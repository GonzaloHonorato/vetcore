package com.vetcore.vetcore.core.atencion.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.vetcore.vetcore.core.atencion.dto.PacienteDto;
import com.vetcore.vetcore.core.atencion.mapper.PacienteMapper;
import com.vetcore.vetcore.core.atencion.model.Paciente;
import com.vetcore.vetcore.core.atencion.repository.PacienteRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class PacienteService {

    private final PacienteMapper mapper;
    private final PacienteRepository repository;

    public List<PacienteDto> findAll() {
        List<Paciente> pacientes = repository.findAll();
        return pacientes.stream()
                .map(mapper::toDto)
                .toList();
    }

    public PacienteDto findById(Integer id) {
        Paciente paciente = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Paciente no encontrado con ID: " + id));
        
        return mapper.toDto(paciente);
    }

    public PacienteDto findByRut(String rut) {
        Paciente paciente = repository.findByRut(rut)
                .orElseThrow(() -> new RuntimeException("Paciente no encontrado con RUT: " + rut));
        
        return mapper.toDto(paciente);
    }

    public PacienteDto create(PacienteDto pacienteDto) {
        Paciente paciente = mapper.toEntity(pacienteDto);
        paciente.setId(null); // Asegurar que es una nueva entidad
        Paciente savedPaciente = repository.save(paciente);
        return mapper.toDto(savedPaciente);
    }

    public PacienteDto update(Integer id, PacienteDto pacienteDto) {
        if (!repository.existsById(id)) {
            throw new RuntimeException("Paciente no encontrado con ID: " + id);
        }
        
        Paciente paciente = mapper.toEntity(pacienteDto);
        paciente.setId(id);
        Paciente updatedPaciente = repository.save(paciente);
        return mapper.toDto(updatedPaciente);
    }

    public void delete(Integer id) {
        if (!repository.existsById(id)) {
            throw new RuntimeException("Paciente no encontrado con ID: " + id);
        }
        repository.deleteById(id);
    }
}
