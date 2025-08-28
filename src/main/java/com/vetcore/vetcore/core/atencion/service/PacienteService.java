package com.vetcore.vetcore.core.atencion.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.vetcore.vetcore.core.atencion.dto.PacienteDto;
import com.vetcore.vetcore.core.atencion.mapper.PacienteMapper;
import com.vetcore.vetcore.core.atencion.model.Paciente;

import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class PacienteService {

    private final PacienteMapper mapper;
    private final List<Paciente> pacientesEnMemoria = new ArrayList<>();

    @PostConstruct
    public void inicializarPacientes() {
        pacientesEnMemoria.addAll(List.of(
            new Paciente(1, "12345678-9", "Juan", "Pérez", LocalDate.of(1985, 5, 15), 
                        "+56912345678", "juan.perez@email.com", "Av. Principal 123", "M", "Soltero"),
            new Paciente(2, "98765432-1", "María", "González", LocalDate.of(1992, 8, 22), 
                        "+56987654321", "maria.gonzalez@email.com", "Calle Secundaria 456", "F", "Casada"),
            new Paciente(3, "11111111-1", "Carlos", "Rodríguez", LocalDate.of(1978, 12, 3), 
                        "+56911111111", "carlos.rodriguez@email.com", "Pasaje Los Robles 789", "M", "Divorciado"),
            new Paciente(4, "22222222-2", "Ana", "Martínez", LocalDate.of(1995, 3, 18), 
                        "+56922222222", "ana.martinez@email.com", "Plaza Central 321", "F", "Soltera"),
            new Paciente(5, "33333333-3", "Pedro", "Silva", LocalDate.of(1980, 7, 10), 
                        "+56933333333", "pedro.silva@email.com", "Boulevard Norte 654", "M", "Casado")
        ));
    }

    public List<PacienteDto> findAll() {
        return pacientesEnMemoria.stream()
                .map(mapper::toDto)
                .toList();
    }

    public PacienteDto findById(Integer id) {
        Optional<Paciente> paciente = pacientesEnMemoria.stream()
                .filter(p -> p.getId().equals(id))
                .findFirst();
        
        if (paciente.isPresent()) {
            return mapper.toDto(paciente.get());
        }
        
        throw new RuntimeException("Paciente no encontrado con ID: " + id);
    }

    public List<PacienteDto> findByRut(String rut) {
        return pacientesEnMemoria.stream()
                .filter(p -> p.getRut().equalsIgnoreCase(rut))
                .map(mapper::toDto)
                .toList();
    }
}
