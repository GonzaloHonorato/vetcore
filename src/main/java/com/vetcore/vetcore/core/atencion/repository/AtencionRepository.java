package com.vetcore.vetcore.core.atencion.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.vetcore.vetcore.core.atencion.model.Atencion;

@Repository
public interface AtencionRepository extends JpaRepository<Atencion, Integer> {
    
    List<Atencion> findByPacienteId(Integer pacienteId);
    
    List<Atencion> findByConsultaId(Integer consultaId);
    
    List<Atencion> findByEspecialidadContainingIgnoreCase(String especialidad);
    
    List<Atencion> findByEstado(String estado);
    
    @Query("SELECT a FROM Atencion a WHERE a.tipoAtencion LIKE %:tipo%")
    List<Atencion> findByTipoAtencionContaining(@Param("tipo") String tipoAtencion);
}
