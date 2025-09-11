package com.vetcore.vetcore.core.atencion.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.vetcore.vetcore.core.atencion.dto.AtencionDto;
import com.vetcore.vetcore.core.atencion.mapper.AtencionMapper;
import com.vetcore.vetcore.core.atencion.model.Atencion;
import com.vetcore.vetcore.core.atencion.repository.AtencionRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AtencionService {

    private final AtencionMapper mapper;
    private final AtencionRepository repository;

    public List<AtencionDto> findAll() {
        List<Atencion> atenciones = repository.findAll();
        return atenciones.stream()
                .map(mapper::toDto)
                .toList();
    }

    public AtencionDto findById(Integer id) {
        Atencion atencion = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Atención no encontrada con ID: " + id));
        
        return mapper.toDto(atencion);
    }

    public List<AtencionDto> findByPacienteId(Integer pacienteId) {
        List<Atencion> atenciones = repository.findByPacienteId(pacienteId);
        return atenciones.stream()
                .map(mapper::toDto)
                .toList();
    }

    public List<AtencionDto> findByConsultaId(Integer consultaId) {
        List<Atencion> atenciones = repository.findByConsultaId(consultaId);
        return atenciones.stream()
                .map(mapper::toDto)
                .toList();
    }

    public List<AtencionDto> findByEspecialidad(String especialidad) {
        List<Atencion> atenciones = repository.findByEspecialidadContainingIgnoreCase(especialidad);
        return atenciones.stream()
                .map(mapper::toDto)
                .toList();
    }

    public AtencionDto create(AtencionDto atencionDto) {
        Atencion atencion = mapper.toEntity(atencionDto);
        atencion.setId(null); // Asegurar que es una nueva entidad
        Atencion savedAtencion = repository.save(atencion);
        return mapper.toDto(savedAtencion);
    }

    public AtencionDto update(Integer id, AtencionDto atencionDto) {
        if (!repository.existsById(id)) {
            throw new RuntimeException("Atención no encontrada con ID: " + id);
        }
        
        Atencion atencion = mapper.toEntity(atencionDto);
        atencion.setId(id);
        Atencion updatedAtencion = repository.save(atencion);
        return mapper.toDto(updatedAtencion);
    }

    public void delete(Integer id) {
        if (!repository.existsById(id)) {
            throw new RuntimeException("Atención no encontrada con ID: " + id);
        }
        repository.deleteById(id);
    }
}
