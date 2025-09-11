package com.vetcore.vetcore.core.atencion.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.vetcore.vetcore.core.atencion.model.Consulta;

@Repository
public interface ConsultaRepository extends JpaRepository<Consulta, Integer> {
    
    List<Consulta> findByPacienteId(Integer pacienteId);
    
    List<Consulta> findByMedicoContainingIgnoreCase(String medico);
    
    @Query("SELECT c FROM Consulta c WHERE c.diagnostico LIKE %:diagnostico%")
    List<Consulta> findByDiagnosticoContaining(@Param("diagnostico") String diagnostico);
    
    @Query("SELECT c FROM Consulta c WHERE c.motivoConsulta LIKE %:motivo%")
    List<Consulta> findByMotivoConsultaContaining(@Param("motivo") String motivoConsulta);
}
