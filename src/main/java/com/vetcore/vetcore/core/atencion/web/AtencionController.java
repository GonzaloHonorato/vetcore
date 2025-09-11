package com.vetcore.vetcore.core.atencion.web;

import java.util.List;

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

import com.vetcore.vetcore.core.atencion.dto.AtencionDto;
import com.vetcore.vetcore.core.atencion.service.AtencionService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/atenciones")
@RequiredArgsConstructor
public class AtencionController {

    private final AtencionService service;

    @GetMapping
    public ResponseEntity<List<AtencionDto>> getAllAtenciones() {
        return ResponseEntity.ok(service.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<AtencionDto> getAtencionById(@PathVariable Integer id) {
        try {
            return ResponseEntity.ok(service.findById(id));
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/paciente/{pacienteId}")
    public ResponseEntity<List<AtencionDto>> getAtencionesByPacienteId(@PathVariable Integer pacienteId) {
        return ResponseEntity.ok(service.findByPacienteId(pacienteId));
    }

    @GetMapping("/consulta/{consultaId}")
    public ResponseEntity<List<AtencionDto>> getAtencionesByConsultaId(@PathVariable Integer consultaId) {
        return ResponseEntity.ok(service.findByConsultaId(consultaId));
    }

    @GetMapping("/especialidad")
    public ResponseEntity<List<AtencionDto>> getAtencionesByEspecialidad(@RequestParam String nombre) {
        return ResponseEntity.ok(service.findByEspecialidad(nombre));
    }

    @PostMapping
    public ResponseEntity<AtencionDto> createAtencion(@RequestBody AtencionDto atencionDto) {
        try {
            AtencionDto created = service.create(atencionDto);
            return ResponseEntity.status(HttpStatus.CREATED).body(created);
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<AtencionDto> updateAtencion(@PathVariable Integer id, @RequestBody AtencionDto atencionDto) {
        try {
            AtencionDto updated = service.update(id, atencionDto);
            return ResponseEntity.ok(updated);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAtencion(@PathVariable Integer id) {
        try {
            service.delete(id);
            return ResponseEntity.noContent().build();
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }
}
