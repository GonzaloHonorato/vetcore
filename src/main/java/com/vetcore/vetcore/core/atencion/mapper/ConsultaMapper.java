package com.vetcore.vetcore.core.atencion.mapper;

import org.springframework.stereotype.Component;

import com.vetcore.vetcore.core.atencion.dto.ConsultaDto;
import com.vetcore.vetcore.core.atencion.model.Consulta;

@Component
public class ConsultaMapper {

    public ConsultaDto toDto(Consulta entity) {
        if (entity == null) {
            return null;
        }
        return new ConsultaDto(
            entity.getId(),
            entity.getPacienteId(),
            entity.getFechaConsulta(),
            entity.getMotivoConsulta(),
            entity.getSintomas(),
            entity.getDiagnostico(),
            entity.getTratamiento(),
            entity.getObservaciones(),
            entity.getMedico()
        );
    }

    public Consulta toEntity(ConsultaDto dto) {
        if (dto == null) {
            return null;
        }
        return new Consulta(
            dto.getId(),
            dto.getPacienteId(),
            dto.getFechaConsulta(),
            dto.getMotivoConsulta(),
            dto.getSintomas(),
            dto.getDiagnostico(),
            dto.getTratamiento(),
            dto.getObservaciones(),
            dto.getMedico()
        );
    }
}
