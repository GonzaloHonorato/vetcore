package com.vetcore.vetcore.core.atencion.web;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.IanaLinkRelations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.vetcore.vetcore.core.atencion.dto.PacienteDto;
import com.vetcore.vetcore.core.atencion.service.PacienteService;

import lombok.RequiredArgsConstructor;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

/**
 * Controlador REST para gestión de pacientes con documentación HATEOAS
 * 
 * Proporciona endpoints para CRUD de pacientes, búsquedas por RUT,
 * y gestión de información médica veterinaria.
 * 
 * Implementa hipermedia usando Spring HATEOAS para facilitar
 * la navegación de la API.
 */

@RestController
@RequestMapping("/api/pacientes")
@RequiredArgsConstructor
public class PacienteController {

    private final PacienteService service;

    /**
     * Lista todos los pacientes con enlaces HATEOAS
     * 
     * @return CollectionModel con todos los pacientes y enlaces de navegación
     */
    @GetMapping
    public ResponseEntity<CollectionModel<EntityModel<PacienteDto>>> getAllPacientes() {
        List<PacienteDto> pacientes = service.findAll();
        
        List<EntityModel<PacienteDto>> pacientesModel = pacientes.stream()
            .map(this::agregarEnlaces)
            .collect(Collectors.toList());
        
        CollectionModel<EntityModel<PacienteDto>> collectionModel = CollectionModel.of(pacientesModel);
        
        // Agregar enlaces de navegación
        collectionModel.add(linkTo(methodOn(PacienteController.class).getAllPacientes()).withSelfRel());
        collectionModel.add(linkTo(methodOn(PacienteController.class).createPaciente(null)).withRel("crear"));
        
        return ResponseEntity.ok(collectionModel);
    }

    /**
     * Busca un paciente por ID con enlaces HATEOAS
     * 
     * @param id ID del paciente a buscar
     * @return EntityModel con el paciente y enlaces relacionados
     */
    @GetMapping("/{id}")
    public ResponseEntity<EntityModel<PacienteDto>> getPacienteById(@PathVariable Integer id) {
        try {
            PacienteDto paciente = service.findById(id);
            return ResponseEntity.ok(agregarEnlaces(paciente));
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/buscar")
    public ResponseEntity<PacienteDto> getPacienteByRut(@RequestParam String rut) {
        try {
            return ResponseEntity.ok(service.findByRut(rut));
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    /**
     * Crea un nuevo paciente con enlaces HATEOAS
     * 
     * @param pacienteDto datos del paciente a crear
     * @return EntityModel con el paciente creado y enlaces relacionados
     */
    @PostMapping
    public ResponseEntity<EntityModel<PacienteDto>> createPaciente(@RequestBody PacienteDto pacienteDto) {
        try {
            PacienteDto created = service.create(pacienteDto);
            EntityModel<PacienteDto> pacienteModel = agregarEnlaces(created);
            
            return ResponseEntity.status(HttpStatus.CREATED).body(pacienteModel);
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }
    
    /**
     * Agrega enlaces HATEOAS a un PacienteDto
     * 
     * @param paciente el paciente al que agregar enlaces
     * @return EntityModel con enlaces HATEOAS
     */
    private EntityModel<PacienteDto> agregarEnlaces(PacienteDto paciente) {
        EntityModel<PacienteDto> pacienteModel = EntityModel.of(paciente);
        
        // Enlace self
        pacienteModel.add(linkTo(methodOn(PacienteController.class).getPacienteById(paciente.getId())).withSelfRel());
        
        // Enlace para actualizar
        pacienteModel.add(linkTo(methodOn(PacienteController.class).updatePaciente(paciente.getId(), null)).withRel("actualizar"));
        
        // Enlace para eliminar
        pacienteModel.add(linkTo(methodOn(PacienteController.class).deletePaciente(paciente.getId())).withRel("eliminar"));
        
        // Enlace para volver a la lista
        pacienteModel.add(linkTo(methodOn(PacienteController.class).getAllPacientes()).withRel(IanaLinkRelations.COLLECTION));
        
        // Enlace para ver consultas del paciente
        pacienteModel.add(linkTo(methodOn(ConsultaController.class).getConsultasByPacienteId(paciente.getId())).withRel("consultas"));
        
        return pacienteModel;
    }

    @PutMapping("/{id}")
    public ResponseEntity<PacienteDto> updatePaciente(@PathVariable Integer id, @RequestBody PacienteDto pacienteDto) {
        try {
            PacienteDto updated = service.update(id, pacienteDto);
            return ResponseEntity.ok(updated);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePaciente(@PathVariable Integer id) {
        try {
            service.delete(id);
            return ResponseEntity.noContent().build();
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }
}
