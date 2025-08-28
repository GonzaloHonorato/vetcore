package com.vetcore.vetcore.core.atencion.web;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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
    public List<AtencionDto> getAllAtenciones() {
        return service.findAll();
    }

    @GetMapping("/{id}")
    public AtencionDto getAtencionById(@PathVariable Integer id) {
        return service.findById(id);
    }

    @GetMapping("/paciente/{pacienteId}")
    public List<AtencionDto> getAtencionesByPacienteId(@PathVariable Integer pacienteId) {
        return service.findByPacienteId(pacienteId);
    }

    @GetMapping("/consulta/{consultaId}")
    public List<AtencionDto> getAtencionesByConsultaId(@PathVariable Integer consultaId) {
        return service.findByConsultaId(consultaId);
    }

    @GetMapping("/especialidad")
    public List<AtencionDto> getAtencionesByEspecialidad(@RequestParam String nombre) {
        return service.findByEspecialidad(nombre);
    }
}
