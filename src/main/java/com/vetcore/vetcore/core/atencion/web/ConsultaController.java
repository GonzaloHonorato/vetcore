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
import org.springframework.web.bind.annotation.RestController;

import com.vetcore.vetcore.core.atencion.dto.ConsultaDto;
import com.vetcore.vetcore.core.atencion.service.ConsultaService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/consultas")
@RequiredArgsConstructor
public class ConsultaController {

    private final ConsultaService service;

    @GetMapping
    public ResponseEntity<List<ConsultaDto>> getAllConsultas() {
        return ResponseEntity.ok(service.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ConsultaDto> getConsultaById(@PathVariable Integer id) {
        try {
            return ResponseEntity.ok(service.findById(id));
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/paciente/{pacienteId}")
    public ResponseEntity<List<ConsultaDto>> getConsultasByPacienteId(@PathVariable Integer pacienteId) {
        return ResponseEntity.ok(service.findByPacienteId(pacienteId));
    }

    @PostMapping
    public ResponseEntity<ConsultaDto> createConsulta(@RequestBody ConsultaDto consultaDto) {
        try {
            ConsultaDto created = service.create(consultaDto);
            return ResponseEntity.status(HttpStatus.CREATED).body(created);
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<ConsultaDto> updateConsulta(@PathVariable Integer id, @RequestBody ConsultaDto consultaDto) {
        try {
            ConsultaDto updated = service.update(id, consultaDto);
            return ResponseEntity.ok(updated);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteConsulta(@PathVariable Integer id) {
        try {
            service.delete(id);
            return ResponseEntity.noContent().build();
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }
}
