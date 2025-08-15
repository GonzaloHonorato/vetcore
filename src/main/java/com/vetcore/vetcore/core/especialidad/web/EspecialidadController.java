package com.vetcore.vetcore.core.especialidad.web;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.vetcore.vetcore.core.especialidad.dto.EspecialidadDto;
import com.vetcore.vetcore.core.especialidad.service.EspecialidadService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/especialidades")
@RequiredArgsConstructor
public class EspecialidadController {

    private final EspecialidadService service;

    @GetMapping
    public List<EspecialidadDto> getAll() {
        return service.findAll();
    }

    @GetMapping("/{id}")
    public EspecialidadDto getById(@PathVariable Integer id) {
        return service.findById(id);
    }

    @PostMapping
    public EspecialidadDto create(@RequestBody EspecialidadDto dto) {
        return service.create(dto);
    }

    @PutMapping("/{id}")
    public EspecialidadDto update(@PathVariable Integer id, @RequestBody EspecialidadDto dto) {
        return service.update(id, dto);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Integer id) {
        service.delete(id);
    }
}