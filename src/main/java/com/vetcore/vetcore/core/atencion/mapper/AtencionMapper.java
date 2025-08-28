package com.vetcore.vetcore.core.atencion.mapper;

import org.springframework.stereotype.Component;

import com.vetcore.vetcore.core.atencion.dto.AtencionDto;
import com.vetcore.vetcore.core.atencion.model.Atencion;

@Component
public class AtencionMapper {

    public AtencionDto toDto(Atencion entity) {
        if (entity == null) {
            return null;
        }
        return new AtencionDto(
            entity.getId(),
            entity.getConsultaId(),
            entity.getPacienteId(),
            entity.getFechaAtencion(),
            entity.getTipoAtencion(),
            entity.getEspecialidad(),
            entity.getResultado(),
            entity.getPrescripciones(),
            entity.getProximaCita(),
            entity.getEstado()
        );
    }

    public Atencion toEntity(AtencionDto dto) {
        if (dto == null) {
            return null;
        }
        return new Atencion(
            dto.getId(),
            dto.getConsultaId(),
            dto.getPacienteId(),
            dto.getFechaAtencion(),
            dto.getTipoAtencion(),
            dto.getEspecialidad(),
            dto.getResultado(),
            dto.getPrescripciones(),
            dto.getProximaCita(),
            dto.getEstado()
        );
    }
}
