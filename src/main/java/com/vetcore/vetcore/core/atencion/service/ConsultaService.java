package com.vetcore.vetcore.core.atencion.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.vetcore.vetcore.core.atencion.dto.ConsultaDto;
import com.vetcore.vetcore.core.atencion.mapper.ConsultaMapper;
import com.vetcore.vetcore.core.atencion.model.Consulta;
import com.vetcore.vetcore.core.atencion.repository.ConsultaRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ConsultaService {

    private final ConsultaMapper mapper;
    private final ConsultaRepository repository;

    public List<ConsultaDto> findAll() {
        List<Consulta> consultas = repository.findAll();
        return consultas.stream()
                .map(mapper::toDto)
                .toList();
    }

    public ConsultaDto findById(Integer id) {
        Consulta consulta = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Consulta no encontrada con ID: " + id));
        
        return mapper.toDto(consulta);
    }

    public List<ConsultaDto> findByPacienteId(Integer pacienteId) {
        List<Consulta> consultas = repository.findByPacienteId(pacienteId);
        return consultas.stream()
                .map(mapper::toDto)
                .toList();
    }

    public ConsultaDto create(ConsultaDto consultaDto) {
        Consulta consulta = mapper.toEntity(consultaDto);
        consulta.setId(null); // Asegurar que es una nueva entidad
        Consulta savedConsulta = repository.save(consulta);
        return mapper.toDto(savedConsulta);
    }

    public ConsultaDto update(Integer id, ConsultaDto consultaDto) {
        if (!repository.existsById(id)) {
            throw new RuntimeException("Consulta no encontrada con ID: " + id);
        }
        
        Consulta consulta = mapper.toEntity(consultaDto);
        consulta.setId(id);
        Consulta updatedConsulta = repository.save(consulta);
        return mapper.toDto(updatedConsulta);
    }

    public void delete(Integer id) {
        if (!repository.existsById(id)) {
            throw new RuntimeException("Consulta no encontrada con ID: " + id);
        }
        repository.deleteById(id);
    }
}
