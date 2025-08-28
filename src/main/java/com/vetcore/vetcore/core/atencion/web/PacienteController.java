package com.vetcore.vetcore.core.atencion.web;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.vetcore.vetcore.core.atencion.dto.PacienteDto;
import com.vetcore.vetcore.core.atencion.service.PacienteService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/pacientes")
@RequiredArgsConstructor
public class PacienteController {

    private final PacienteService service;

    @GetMapping
    public List<PacienteDto> getAllPacientes() {
        return service.findAll();
    }

    @GetMapping("/{id}")
    public PacienteDto getPacienteById(@PathVariable Integer id) {
        return service.findById(id);
    }

    @GetMapping("/buscar")
    public List<PacienteDto> getPacienteByRut(@RequestParam String rut) {
        return service.findByRut(rut);
    }
}
