package com.vetcore.vetcore.core.atencion.model;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Atencion {
    
    private Integer id;
    private Integer consultaId;
    private Integer pacienteId;
    private LocalDateTime fechaAtencion;
    private String tipoAtencion;
    private String especialidad;
    private String resultado;
    private String prescripciones;
    private String proximaCita;
    private String estado;
}
