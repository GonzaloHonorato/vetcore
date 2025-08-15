package com.vetcore.vetcore.core.especialidad.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.vetcore.vetcore.core.especialidad.model.Especialidad;

public interface EspecialidadRepository extends JpaRepository<Especialidad, Integer> {
    boolean existsByNombre(String nombre);
}