package com.vetcore.vetcore.core.atencion.model;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "atenciones")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Atencion {
    
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "atencion_seq")
    @SequenceGenerator(name = "atencion_seq", sequenceName = "ATENCION_SEQ", allocationSize = 1)
    private Integer id;
    
    @Column(name = "consulta_id", nullable = false)
    private Integer consultaId;
    
    @Column(name = "paciente_id", nullable = false)
    private Integer pacienteId;
    
    @Column(name = "fecha_atencion", nullable = false)
    private LocalDateTime fechaAtencion;
    
    @Column(name = "tipo_atencion", length = 100)
    private String tipoAtencion;
    
    @Column(name = "especialidad", length = 100)
    private String especialidad;
    
    @Column(name = "resultado", columnDefinition = "CLOB")
    private String resultado;
    
    @Column(name = "prescripciones", columnDefinition = "CLOB")
    private String prescripciones;
    
    @Column(name = "proxima_cita", length = 200)
    private String proximaCita;
    
    @Column(name = "estado", length = 50)
    private String estado;
}
