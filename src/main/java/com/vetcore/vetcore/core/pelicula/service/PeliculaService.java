package com.vetcore.vetcore.core.pelicula.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.vetcore.vetcore.core.pelicula.dto.PeliculaDto;
import com.vetcore.vetcore.core.pelicula.mapper.PeliculaMapper;
import com.vetcore.vetcore.core.pelicula.model.Pelicula;

import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class PeliculaService {

    private final PeliculaMapper mapper;
    private final List<Pelicula> peliculasEnMemoria = new ArrayList<>();

    @PostConstruct
    public void inicializarPeliculas() {
        peliculasEnMemoria.addAll(List.of(
            new Pelicula(1, "Titanic", 1997, "James Cameron", "Romance", "Una historia de amor épica a bordo del barco más famoso de la historia"),
            new Pelicula(2, "El Padrino", 1972, "Francis Ford Coppola", "Drama", "La saga de una familia mafiosa italiana en Estados Unidos"),
            new Pelicula(3, "Pulp Fiction", 1994, "Quentin Tarantino", "Thriller", "Historias entrelazadas de crimen y redención en Los Ángeles"),
            new Pelicula(4, "El Señor de los Anillos: La Comunidad del Anillo", 2001, "Peter Jackson", "Fantasía", "Un hobbit emprende un viaje épico para destruir un anillo mágico"),
            new Pelicula(5, "Matrix", 1999, "Lana Wachowski", "Ciencia Ficción", "Un hacker descubre que la realidad es una simulación computacional"),
            new Pelicula(6, "Forrest Gump", 1994, "Robert Zemeckis", "Drama", "La extraordinaria vida de un hombre con discapacidad intelectual"),
            new Pelicula(7, "El Rey León", 1994, "Roger Allers", "Animación", "Un joven león debe reclamar su lugar como rey de la sabana africana")
        ));
    }

    public List<PeliculaDto> findAll() {
        return peliculasEnMemoria.stream()
                .map(mapper::toDto)
                .toList();
    }

    public PeliculaDto findById(Integer id) {
        Optional<Pelicula> pelicula = peliculasEnMemoria.stream()
                .filter(p -> p.getId().equals(id))
                .findFirst();
        
        if (pelicula.isPresent()) {
            return mapper.toDto(pelicula.get());
        }
        
        throw new RuntimeException("Película no encontrada con ID: " + id);
    }
}
