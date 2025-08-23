package com.vetcore.vetcore.core.pelicula.web;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.vetcore.vetcore.core.pelicula.dto.PeliculaDto;
import com.vetcore.vetcore.core.pelicula.service.PeliculaService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/peliculas")
@RequiredArgsConstructor
public class PeliculaController {

    private final PeliculaService service;

    @GetMapping
    public List<PeliculaDto> getAllPeliculas() {
        return service.findAll();
    }

    @GetMapping("/{id}")
    public PeliculaDto getPeliculaById(@PathVariable Integer id) {
        return service.findById(id);
    }
}
