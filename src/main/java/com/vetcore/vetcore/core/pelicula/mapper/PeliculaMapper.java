package com.vetcore.vetcore.core.pelicula.mapper;

import org.springframework.stereotype.Component;

import com.vetcore.vetcore.core.pelicula.dto.PeliculaDto;
import com.vetcore.vetcore.core.pelicula.model.Pelicula;

@Component
public class PeliculaMapper {

    public PeliculaDto toDto(Pelicula entity) {
        if (entity == null) {
            return null;
        }
        return new PeliculaDto(
            entity.getId(),
            entity.getTitulo(),
            entity.getAnio(),
            entity.getDirector(),
            entity.getGenero(),
            entity.getSinopsis()
        );
    }

    public Pelicula toEntity(PeliculaDto dto) {
        if (dto == null) {
            return null;
        }
        return new Pelicula(
            dto.getId(),
            dto.getTitulo(),
            dto.getAnio(),
            dto.getDirector(),
            dto.getGenero(),
            dto.getSinopsis()
        );
    }
}
