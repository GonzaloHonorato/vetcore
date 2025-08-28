package com.vetcore.vetcore.core.atencion.dto;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ConsultaDto {
    
    private Integer id;
    private Integer pacienteId;
    private LocalDateTime fechaConsulta;
    private String motivoConsulta;
    private String sintomas;
    private String diagnostico;
    private String tratamiento;
    private String observaciones;
    private String medico;
}
