package com.vetcore.vetcore.core.atencion.web;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.CollectionModel;
import org.springframework.http.ResponseEntity;

import com.vetcore.vetcore.core.atencion.dto.PacienteDto;
import com.vetcore.vetcore.core.atencion.service.PacienteService;

/**
 * Pruebas unitarias para PacienteController
 * 
 * Esta clase contiene pruebas unitarias que verifican el correcto
 * funcionamiento del controlador de pacientes veterinarios sin necesidad de 
 * cargar el contexto completo de Spring.
 * 
 * Utiliza Mockito para simular las dependencias y verifica
 * la implementación correcta de enlaces HATEOAS.
 * 
 * @author Sistema Veterinario
 * @version 1.0
 */
@ExtendWith(MockitoExtension.class)
public class PacienteControllerTest {
    
    @Mock
    private PacienteService pacienteService;
    
    @InjectMocks
    private PacienteController pacienteController;
    
    private PacienteDto pacienteDto;
    
    /**
     * Configuración inicial para cada prueba
     * Se ejecuta antes de cada método de prueba (@Test)
     */
    @BeforeEach
    public void setUp() {
        // Configurar datos de prueba
        pacienteDto = new PacienteDto();
        pacienteDto.setId(1);
        pacienteDto.setRut("12345678-9");
        pacienteDto.setNombre("Max");
        pacienteDto.setApellido("Perrito");
        pacienteDto.setFechaNacimiento(LocalDate.of(2020, 5, 15));
        pacienteDto.setTelefono("+56912345678");
        pacienteDto.setEmail("dueno@email.com");
        pacienteDto.setSexo("M");
        pacienteDto.setEstadoCivil("Soltero");
    }
    
    /**
     * Prueba unitaria para getAllPacientes
     * 
     * Verifica que:
     * 1. El controlador llame al servicio correctamente
     * 2. Retorne un ResponseEntity con status 200
     * 3. El contenido sea un CollectionModel con los pacientes
     * 4. Se incluyan enlaces HATEOAS de navegación
     * 
     * Escenario: Consulta exitosa de todos los pacientes
     * Entrada: Ninguna
     * Salida esperada: CollectionModel con pacientes y enlaces
     */
    @Test
    public void testGetAllPacientes_DeberiaRetornarPacientesConHATEOAS() {
        // Arrange
        List<PacienteDto> listaPacientes = Arrays.asList(pacienteDto);
        when(pacienteService.findAll()).thenReturn(listaPacientes);
        
        // Act
        ResponseEntity<CollectionModel<EntityModel<PacienteDto>>> response = pacienteController.getAllPacientes();
        
        // Assert
        assertNotNull(response, "La respuesta no debe ser null");
        assertTrue(response.getStatusCode().is2xxSuccessful(), "El status debe ser 2xx");
        assertNotNull(response.getBody(), "El body no debe ser null");
        
        CollectionModel<EntityModel<PacienteDto>> collectionModel = response.getBody();
        assertNotNull(collectionModel.getContent(), "El contenido no debe ser null");
        assertFalse(collectionModel.getContent().isEmpty(), "Debe tener al menos un paciente");
        
        // Verificar que se incluyan enlaces HATEOAS
        assertTrue(collectionModel.hasLinks(), "Debe tener enlaces HATEOAS");
        assertTrue(collectionModel.hasLink("self"), "Debe tener enlace self");
        assertTrue(collectionModel.hasLink("crear"), "Debe tener enlace crear");
        
        // Verificar que cada paciente tenga sus propios enlaces
        EntityModel<PacienteDto> primerPaciente = collectionModel.getContent().iterator().next();
        assertTrue(primerPaciente.hasLinks(), "Cada paciente debe tener enlaces");
        assertTrue(primerPaciente.hasLink("self"), "Cada paciente debe tener enlace self");
        
        // Verificar que se llamó al servicio
        verify(pacienteService, times(1)).findAll();
    }
    
    /**
     * Prueba unitaria para getPacienteById con ID existente
     * 
     * Verifica que:
     * 1. El controlador llame al servicio correctamente
     * 2. Retorne un ResponseEntity con status 200
     * 3. El contenido sea un EntityModel con el paciente
     * 4. Se incluyan enlaces HATEOAS apropiados
     * 
     * Escenario: Búsqueda exitosa de un paciente específico
     * Entrada: ID = 1
     * Salida esperada: ResponseEntity con EntityModel<PacienteDto>
     */
    @Test
    public void testGetPacienteById_ConIdExistente_DeberiaRetornarPacienteConHATEOAS() {
        // Arrange
        Integer idPaciente = 1;
        when(pacienteService.findById(idPaciente)).thenReturn(pacienteDto);
        
        // Act
        ResponseEntity<EntityModel<PacienteDto>> response = pacienteController.getPacienteById(idPaciente);
        
        // Assert
        assertNotNull(response, "La respuesta no debe ser null");
        assertTrue(response.getStatusCode().is2xxSuccessful(), "El status debe ser 2xx");
        assertNotNull(response.getBody(), "El body no debe ser null");
        
        EntityModel<PacienteDto> entityModel = response.getBody();
        assertNotNull(entityModel.getContent(), "El contenido no debe ser null");
        assertEquals(pacienteDto.getId(), entityModel.getContent().getId(), "El ID debe coincidir");
        assertEquals(pacienteDto.getNombre(), entityModel.getContent().getNombre(), "El nombre debe coincidir");
        assertEquals("Max", entityModel.getContent().getNombre(), "El nombre debe ser 'Max'");
        
        // Verificar que se incluyan enlaces HATEOAS
        assertTrue(entityModel.hasLinks(), "Debe tener enlaces HATEOAS");
        assertTrue(entityModel.hasLink("self"), "Debe tener enlace self");
        assertTrue(entityModel.hasLink("actualizar"), "Debe tener enlace actualizar");
        assertTrue(entityModel.hasLink("eliminar"), "Debe tener enlace eliminar");
        assertTrue(entityModel.hasLink("collection"), "Debe tener enlace collection");
        assertTrue(entityModel.hasLink("consultas"), "Debe tener enlace consultas");
        
        // Verificar que se llamó al servicio
        verify(pacienteService, times(1)).findById(idPaciente);
    }
    
    /**
     * Prueba unitaria para getPacienteById con ID inexistente
     * 
     * Verifica que:
     * 1. El controlador llame al servicio correctamente
     * 2. Retorne un ResponseEntity con status 404
     * 3. No haya contenido en la respuesta
     * 
     * Escenario: Búsqueda de un paciente que no existe
     * Entrada: ID = 999
     * Salida esperada: ResponseEntity con status 404
     */
    @Test
    public void testGetPacienteById_ConIdInexistente_DeberiaRetornar404() {
        // Arrange
        Integer idInexistente = 999;
        when(pacienteService.findById(idInexistente)).thenThrow(new RuntimeException("Paciente no encontrado"));
        
        // Act
        ResponseEntity<EntityModel<PacienteDto>> response = pacienteController.getPacienteById(idInexistente);
        
        // Assert
        assertNotNull(response, "La respuesta no debe ser null");
        assertTrue(response.getStatusCode().is4xxClientError(), "El status debe ser 4xx");
        assertNull(response.getBody(), "El body debe ser null para 404");
        
        // Verificar que se llamó al servicio
        verify(pacienteService, times(1)).findById(idInexistente);
    }
    
    /**
     * Prueba unitaria para createPaciente con datos válidos
     * 
     * Verifica que:
     * 1. El controlador llame al servicio correctamente
     * 2. Retorne un ResponseEntity con status 201 Created
     * 3. El contenido sea un EntityModel con el paciente creado
     * 4. Se incluyan enlaces HATEOAS apropiados
     * 
     * Escenario: Creación exitosa de un nuevo paciente
     * Entrada: PacienteDto con datos válidos
     * Salida esperada: ResponseEntity con status 201 y EntityModel
     */
    @Test
    public void testCreatePaciente_ConDatosValidos_DeberiaRetornarPacienteCreadoConHATEOAS() {
        // Arrange
        PacienteDto nuevoPaciente = new PacienteDto();
        nuevoPaciente.setRut("98765432-1");
        nuevoPaciente.setNombre("Luna");
        nuevoPaciente.setApellido("Gatita");
        nuevoPaciente.setTelefono("+56987654321");
        
        PacienteDto pacienteCreado = new PacienteDto();
        pacienteCreado.setId(2);
        pacienteCreado.setRut("98765432-1");
        pacienteCreado.setNombre("Luna");
        pacienteCreado.setApellido("Gatita");
        pacienteCreado.setTelefono("+56987654321");
        
        when(pacienteService.create(any(PacienteDto.class))).thenReturn(pacienteCreado);
        
        // Act
        ResponseEntity<EntityModel<PacienteDto>> response = pacienteController.createPaciente(nuevoPaciente);
        
        // Assert
        assertNotNull(response, "La respuesta no debe ser null");
        assertTrue(response.getStatusCode().is2xxSuccessful(), "El status debe ser 2xx (201 Created)");
        assertNotNull(response.getBody(), "El body no debe ser null");
        
        EntityModel<PacienteDto> entityModel = response.getBody();
        assertNotNull(entityModel.getContent(), "El contenido no debe ser null");
        assertEquals(pacienteCreado.getId(), entityModel.getContent().getId(), "El ID debe coincidir");
        assertEquals(pacienteCreado.getNombre(), entityModel.getContent().getNombre(), "El nombre debe coincidir");
        assertEquals("Luna", entityModel.getContent().getNombre(), "El nombre debe ser 'Luna'");
        
        // Verificar que se incluyan enlaces HATEOAS
        assertTrue(entityModel.hasLinks(), "Debe tener enlaces HATEOAS");
        assertTrue(entityModel.hasLink("self"), "Debe tener enlace self");
        
        // Verificar que se llamó al servicio de creación
        verify(pacienteService, times(1)).create(any(PacienteDto.class));
    }
    
    /**
     * Método que se ejecuta después de cada prueba
     * Limpia y resetea los mocks
     */
    @AfterEach
    public void tearDown() {
        reset(pacienteService);
    }
}