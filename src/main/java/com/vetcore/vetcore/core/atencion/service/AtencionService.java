package com.vetcore.vetcore.core.atencion.service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.vetcore.vetcore.core.atencion.dto.AtencionDto;
import com.vetcore.vetcore.core.atencion.mapper.AtencionMapper;
import com.vetcore.vetcore.core.atencion.model.Atencion;

import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AtencionService {

    private final AtencionMapper mapper;
    private final List<Atencion> atencionesEnMemoria = new ArrayList<>();

    @PostConstruct
    public void inicializarAtenciones() {
        atencionesEnMemoria.addAll(List.of(
            new Atencion(1, 1, 1, LocalDateTime.of(2025, 8, 15, 10, 0), 
                        "Consulta médica", "Neurología", "Tratamiento iniciado exitosamente", 
                        "Ibuprofeno 400mg cada 8 horas por 5 días", "2025-09-15", "Completada"),
            new Atencion(2, 2, 2, LocalDateTime.of(2025, 8, 16, 14, 45), 
                        "Control prenatal", "Ginecología", "Embarazo evolucionando normalmente", 
                        "Ácido fólico 5mg diarios", "2025-09-16", "Completada"),
            new Atencion(3, 3, 3, LocalDateTime.of(2025, 8, 17, 11, 30), 
                        "Evaluación cardiológica", "Cardiología", "Exámenes cardiológicos normales", 
                        "Alprazolam 0.5mg según necesidad", "2025-09-17", "Completada"),
            new Atencion(4, 4, 1, LocalDateTime.of(2025, 8, 20, 17, 15), 
                        "Seguimiento neurológico", "Neurología", "Paciente en mejora progresiva", 
                        "Reducir dosis de analgésicos", "2025-09-05", "En proceso"),
            new Atencion(5, 5, 4, LocalDateTime.of(2025, 8, 22, 11, 0), 
                        "Chequeo preventivo", "Medicina General", "Exámenes de laboratorio normales", 
                        "Multivitamínicos diarios", "2026-08-22", "Completada"),
            new Atencion(6, 1, 1, LocalDateTime.of(2025, 8, 25, 9, 0), 
                        "Terapia física", "Kinesiología", "Inicio de terapia para migraña tensional", 
                        "Ejercicios de relajación cervical", "2025-09-01", "Programada")
        ));
    }

    public List<AtencionDto> findAll() {
        return atencionesEnMemoria.stream()
                .map(mapper::toDto)
                .toList();
    }

    public AtencionDto findById(Integer id) {
        Optional<Atencion> atencion = atencionesEnMemoria.stream()
                .filter(a -> a.getId().equals(id))
                .findFirst();
        
        if (atencion.isPresent()) {
            return mapper.toDto(atencion.get());
        }
        
        throw new RuntimeException("Atención no encontrada con ID: " + id);
    }

    public List<AtencionDto> findByPacienteId(Integer pacienteId) {
        return atencionesEnMemoria.stream()
                .filter(a -> a.getPacienteId().equals(pacienteId))
                .map(mapper::toDto)
                .toList();
    }

    public List<AtencionDto> findByConsultaId(Integer consultaId) {
        return atencionesEnMemoria.stream()
                .filter(a -> a.getConsultaId().equals(consultaId))
                .map(mapper::toDto)
                .toList();
    }

    public List<AtencionDto> findByEspecialidad(String especialidad) {
        return atencionesEnMemoria.stream()
                .filter(a -> a.getEspecialidad().toLowerCase().contains(especialidad.toLowerCase()))
                .map(mapper::toDto)
                .toList();
    }
}
