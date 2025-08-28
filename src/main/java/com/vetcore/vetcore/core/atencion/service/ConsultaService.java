package com.vetcore.vetcore.core.atencion.service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.vetcore.vetcore.core.atencion.dto.ConsultaDto;
import com.vetcore.vetcore.core.atencion.mapper.ConsultaMapper;
import com.vetcore.vetcore.core.atencion.model.Consulta;

import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ConsultaService {

    private final ConsultaMapper mapper;
    private final List<Consulta> consultasEnMemoria = new ArrayList<>();

    @PostConstruct
    public void inicializarConsultas() {
        consultasEnMemoria.addAll(List.of(
            new Consulta(1, 1, LocalDateTime.of(2025, 8, 15, 9, 30), 
                        "Dolor de cabeza constante", "Cefalea, mareos, náuseas", 
                        "Migraña tensional", "Analgésicos y relajantes musculares", 
                        "Reducir estrés, buena hidratación", "Dr. Fernández"),
            new Consulta(2, 2, LocalDateTime.of(2025, 8, 16, 14, 15), 
                        "Control embarazo", "Náuseas matutinas, fatiga", 
                        "Embarazo de 12 semanas normal", "Vitaminas prenatales", 
                        "Control mensual, dieta equilibrada", "Dra. López"),
            new Consulta(3, 3, LocalDateTime.of(2025, 8, 17, 11, 0), 
                        "Dolor en el pecho", "Dolor torácico, dificultad respiratoria", 
                        "Ansiedad generalizada", "Ansiolíticos suaves", 
                        "Terapia psicológica recomendada", "Dr. Morales"),
            new Consulta(4, 1, LocalDateTime.of(2025, 8, 20, 16, 45), 
                        "Seguimiento migraña", "Mejora del dolor de cabeza", 
                        "Evolución favorable de migraña", "Continuar tratamiento", 
                        "Control en 2 semanas", "Dr. Fernández"),
            new Consulta(5, 4, LocalDateTime.of(2025, 8, 22, 10, 30), 
                        "Examen preventivo", "Asintomática", 
                        "Estado de salud normal", "Mantener estilo de vida saludable", 
                        "Control anual", "Dra. Castro")
        ));
    }

    public List<ConsultaDto> findAll() {
        return consultasEnMemoria.stream()
                .map(mapper::toDto)
                .toList();
    }

    public ConsultaDto findById(Integer id) {
        Optional<Consulta> consulta = consultasEnMemoria.stream()
                .filter(c -> c.getId().equals(id))
                .findFirst();
        
        if (consulta.isPresent()) {
            return mapper.toDto(consulta.get());
        }
        
        throw new RuntimeException("Consulta no encontrada con ID: " + id);
    }

    public List<ConsultaDto> findByPacienteId(Integer pacienteId) {
        return consultasEnMemoria.stream()
                .filter(c -> c.getPacienteId().equals(pacienteId))
                .map(mapper::toDto)
                .toList();
    }
}
