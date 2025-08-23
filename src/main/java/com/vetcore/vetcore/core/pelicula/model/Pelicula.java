package com.vetcore.vetcore.core.pelicula.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Pelicula {
    
    private Integer id;
    private String titulo;
    private Integer anio;
    private String director;
    private String genero;
    private String sinopsis;
}
