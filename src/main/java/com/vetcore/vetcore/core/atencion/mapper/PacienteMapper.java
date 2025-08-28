package com.vetcore.vetcore.core.atencion.mapper;

import org.springframework.stereotype.Component;

import com.vetcore.vetcore.core.atencion.dto.PacienteDto;
import com.vetcore.vetcore.core.atencion.model.Paciente;

@Component
public class PacienteMapper {

    public PacienteDto toDto(Paciente entity) {
        if (entity == null) {
            return null;
        }
        return new PacienteDto(
            entity.getId(),
            entity.getRut(),
            entity.getNombre(),
            entity.getApellido(),
            entity.getFechaNacimiento(),
            entity.getTelefono(),
            entity.getEmail(),
            entity.getDireccion(),
            entity.getSexo(),
            entity.getEstadoCivil()
        );
    }

    public Paciente toEntity(PacienteDto dto) {
        if (dto == null) {
            return null;
        }
        return new Paciente(
            dto.getId(),
            dto.getRut(),
            dto.getNombre(),
            dto.getApellido(),
            dto.getFechaNacimiento(),
            dto.getTelefono(),
            dto.getEmail(),
            dto.getDireccion(),
            dto.getSexo(),
            dto.getEstadoCivil()
        );
    }
}
