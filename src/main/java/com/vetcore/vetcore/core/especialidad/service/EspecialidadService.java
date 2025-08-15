package com.vetcore.vetcore.core.especialidad.service;

import com.vetcore.vetcore.core.especialidad.dto.EspecialidadDto;
import com.vetcore.vetcore.core.especialidad.mapper.EspecialidadMapper;
import com.vetcore.vetcore.core.especialidad.model.Especialidad;
import com.vetcore.vetcore.core.especialidad.repository.EspecialidadRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class EspecialidadService {

    private final EspecialidadRepository repository;
    private final EspecialidadMapper mapper;

    public List<EspecialidadDto> findAll() {
        return repository.findAll().stream()
                .map(mapper::toDto)
                .collect(Collectors.toList());
    }

    public EspecialidadDto findById(Integer id) {
        Especialidad entity = repository.findById(id).orElseThrow(() -> new RuntimeException("Especialidad no encontrada"));
        return mapper.toDto(entity);
    }

    public EspecialidadDto create(EspecialidadDto dto) {
        if (repository.existsByNombre(dto.getNombre())) {
            throw new RuntimeException("Ya existe una especialidad con ese nombre");
        }
        Especialidad saved = repository.save(mapper.toEntity(dto));
        return mapper.toDto(saved);
    }

    public EspecialidadDto update(Integer id, EspecialidadDto dto) {
        Especialidad existing = repository.findById(id).orElseThrow(() -> new RuntimeException("Especialidad no encontrada"));
        existing.setNombre(dto.getNombre());
        return mapper.toDto(repository.save(existing));
    }

    public void delete(Integer id) {
        repository.deleteById(id);
    }
}