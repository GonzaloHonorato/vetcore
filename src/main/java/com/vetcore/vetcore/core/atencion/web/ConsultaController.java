package com.vetcore.vetcore.core.atencion.web;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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
    public List<ConsultaDto> getAllConsultas() {
        return service.findAll();
    }

    @GetMapping("/{id}")
    public ConsultaDto getConsultaById(@PathVariable Integer id) {
        return service.findById(id);
    }

    @GetMapping("/paciente/{pacienteId}")
    public List<ConsultaDto> getConsultasByPacienteId(@PathVariable Integer pacienteId) {
        return service.findByPacienteId(pacienteId);
    }
}
