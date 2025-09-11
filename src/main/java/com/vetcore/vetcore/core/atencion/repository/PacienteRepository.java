package com.vetcore.vetcore.core.atencion.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.vetcore.vetcore.core.atencion.model.Paciente;

@Repository
public interface PacienteRepository extends JpaRepository<Paciente, Integer> {
    
    Optional<Paciente> findByRut(String rut);
    
    List<Paciente> findByNombreContainingIgnoreCaseOrApellidoContainingIgnoreCase(String nombre, String apellido);
    
    @Query("SELECT p FROM Paciente p WHERE p.email = :email")
    Optional<Paciente> findByEmail(@Param("email") String email);
    
    List<Paciente> findBySexo(String sexo);
    
    List<Paciente> findByEstadoCivil(String estadoCivil);
}
