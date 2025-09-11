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
@Table(name = "consultas")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Consulta {
    
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "consulta_seq")
    @SequenceGenerator(name = "consulta_seq", sequenceName = "CONSULTA_SEQ", allocationSize = 1)
    private Integer id;
    
    @Column(name = "paciente_id", nullable = false)
    private Integer pacienteId;
    
    @Column(name = "fecha_consulta", nullable = false)
    private LocalDateTime fechaConsulta;
    
    @Column(name = "motivo_consulta", columnDefinition = "CLOB")
    private String motivoConsulta;
    
    @Column(name = "sintomas", columnDefinition = "CLOB")
    private String sintomas;
    
    @Column(name = "diagnostico", columnDefinition = "CLOB")
    private String diagnostico;
    
    @Column(name = "tratamiento", columnDefinition = "CLOB")
    private String tratamiento;
    
    @Column(name = "observaciones", columnDefinition = "CLOB")
    private String observaciones;
    
    @Column(name = "medico", length = 100)
    private String medico;
}
