package com.vetcore.vetcore.core.atencion.model;

import java.time.LocalDate;

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
@Table(name = "pacientes")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Paciente {
    
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "paciente_seq")
    @SequenceGenerator(name = "paciente_seq", sequenceName = "PACIENTE_SEQ", allocationSize = 1)
    private Integer id;
    
    @Column(name = "rut", unique = true, nullable = false, length = 12)
    private String rut;
    
    @Column(name = "nombre", nullable = false, length = 100)
    private String nombre;
    
    @Column(name = "apellido", nullable = false, length = 100)
    private String apellido;
    
    @Column(name = "fecha_nacimiento", nullable = false)
    private LocalDate fechaNacimiento;
    
    @Column(name = "telefono", length = 20)
    private String telefono;
    
    @Column(name = "email", length = 150)
    private String email;
    
    @Column(name = "direccion", length = 255)
    private String direccion;
    
    @Column(name = "sexo", length = 1)
    private String sexo;
    
    @Column(name = "estado_civil", length = 20)
    private String estadoCivil;
}
