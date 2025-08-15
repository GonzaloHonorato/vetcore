package com.vetcore.vetcore.core.especialidad.mapper;

import com.vetcore.vetcore.core.especialidad.dto.EspecialidadDto;
import com.vetcore.vetcore.core.especialidad.model.Especialidad;
import org.springframework.stereotype.Component;

@Component
public class EspecialidadMapper {

    public EspecialidadDto toDto(Especialidad entity) {
        if (entity == null) {
            return null;
        }
        return new EspecialidadDto(entity.getId(), entity.getNombre());
    }

    public Especialidad toEntity(EspecialidadDto dto) {
        if (dto == null) {
            return null;
        }
        Especialidad entity = new Especialidad();
        entity.setId(dto.getId());
        entity.setNombre(dto.getNombre());
        return entity;
    }
}