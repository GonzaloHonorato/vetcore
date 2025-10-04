package com.vetcore.vetcore.core.atencion.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.vetcore.vetcore.core.atencion.dto.PacienteDto;
import com.vetcore.vetcore.core.atencion.mapper.PacienteMapper;
import com.vetcore.vetcore.core.atencion.model.Paciente;
import com.vetcore.vetcore.core.atencion.repository.PacienteRepository;

/**
 * Pruebas unitarias para PacienteService
 * 
 * Esta clase contiene las pruebas unitarias que verifican el correcto
 * funcionamiento de los métodos del servicio de pacientes veterinarios.
 * 
 * Utiliza Mockito para simular las dependencias y JUnit 5 para 
 * las aserciones y estructura de las pruebas.
 * 
 * @author Sistema Veterinario
 * @version 1.0
 */
@ExtendWith(MockitoExtension.class)
public class PacienteServiceTest {
    
    @Mock
    private PacienteRepository pacienteRepository;
    
    @Mock
    private PacienteMapper pacienteMapper;
    
    @InjectMocks
    private PacienteService pacienteService;
    
    private Paciente pacienteEjemplo;
    private PacienteDto pacienteDtoEjemplo;
    
    /**
     * Configuración inicial para cada prueba
     * Se ejecuta antes de cada método de prueba (@Test)
     */
    @BeforeEach
    public void setUp() {
        // Configurar datos de prueba
        pacienteEjemplo = new Paciente();
        pacienteEjemplo.setId(1);
        pacienteEjemplo.setRut("12345678-9");
        pacienteEjemplo.setNombre("Max");
        pacienteEjemplo.setApellido("Perrito");
        pacienteEjemplo.setFechaNacimiento(LocalDate.of(2020, 5, 15));
        pacienteEjemplo.setTelefono("+56912345678");
        pacienteEjemplo.setEmail("dueno@email.com");
        
        pacienteDtoEjemplo = new PacienteDto();
        pacienteDtoEjemplo.setId(1);
        pacienteDtoEjemplo.setRut("12345678-9");
        pacienteDtoEjemplo.setNombre("Max");
        pacienteDtoEjemplo.setApellido("Perrito");
        pacienteDtoEjemplo.setFechaNacimiento(LocalDate.of(2020, 5, 15));
        pacienteDtoEjemplo.setTelefono("+56912345678");
        pacienteDtoEjemplo.setEmail("dueno@email.com");
    }
    
    /**
     * Prueba unitaria para el método findAll()
     * 
     * Verifica que:
     * 1. Se llame al repositorio para obtener todos los pacientes
     * 2. Se mapeé correctamente cada paciente a DTO
     * 3. Se retorne la lista esperada de PacienteDto
     * 
     * Escenario: Consulta exitosa de todos los pacientes
     * Entrada: Sin parámetros
     * Salida esperada: Lista de PacienteDto con los datos mapeados
     */
    @Test
    public void testFindAll_DeberiaRetornarListaDePacientes() {
        // Arrange (Preparar)
        List<Paciente> listPacientes = Arrays.asList(pacienteEjemplo);
        when(pacienteRepository.findAll()).thenReturn(listPacientes);
        when(pacienteMapper.toDto(pacienteEjemplo)).thenReturn(pacienteDtoEjemplo);
        
        // Act (Actuar)
        List<PacienteDto> resultado = pacienteService.findAll();
        
        // Assert (Verificar)
        assertNotNull(resultado, "El resultado no debe ser null");
        assertEquals(1, resultado.size(), "Debe retornar exactamente 1 paciente");
        assertEquals(pacienteDtoEjemplo.getId(), resultado.get(0).getId(), "El ID debe coincidir");
        assertEquals(pacienteDtoEjemplo.getRut(), resultado.get(0).getRut(), "El RUT debe coincidir");
        assertEquals(pacienteDtoEjemplo.getNombre(), resultado.get(0).getNombre(), "El nombre debe coincidir");
        
        // Verificar que se llamaron los métodos correctos
        verify(pacienteRepository, times(1)).findAll();
        verify(pacienteMapper, times(1)).toDto(pacienteEjemplo);
    }
    
    /**
     * Prueba unitaria para el método findById()
     * 
     * Verifica que:
     * 1. Se llame al repositorio con el ID correcto
     * 2. Se mapeé el paciente encontrado a DTO
     * 3. Se retorne el PacienteDto correcto
     * 
     * Escenario: Búsqueda exitosa de un paciente por ID existente
     * Entrada: ID = 1
     * Salida esperada: PacienteDto con los datos del paciente
     */
    @Test
    public void testFindById_ConIdExistente_DeberiaRetornarPaciente() {
        // Arrange
        Integer idBuscado = 1;
        when(pacienteRepository.findById(idBuscado)).thenReturn(Optional.of(pacienteEjemplo));
        when(pacienteMapper.toDto(pacienteEjemplo)).thenReturn(pacienteDtoEjemplo);
        
        // Act
        PacienteDto resultado = pacienteService.findById(idBuscado);
        
        // Assert
        assertNotNull(resultado, "El resultado no debe ser null");
        assertEquals(pacienteDtoEjemplo.getId(), resultado.getId(), "El ID debe coincidir");
        assertEquals(pacienteDtoEjemplo.getRut(), resultado.getRut(), "El RUT debe coincidir");
        assertEquals(pacienteDtoEjemplo.getNombre(), resultado.getNombre(), "El nombre debe coincidir");
        assertEquals("Max", resultado.getNombre(), "El nombre debe ser 'Max'");
        
        // Verificar interacciones con mocks
        verify(pacienteRepository, times(1)).findById(idBuscado);
        verify(pacienteMapper, times(1)).toDto(pacienteEjemplo);
    }
    
    /**
     * Prueba unitaria para findById con ID inexistente
     * 
     * Verifica que:
     * 1. Se llame al repositorio con el ID correcto
     * 2. Se lance una RuntimeException cuando no existe el paciente
     * 3. No se llame al mapper cuando no hay datos
     * 
     * Escenario: Búsqueda de un paciente con ID que no existe
     * Entrada: ID = 999 (inexistente)
     * Salida esperada: RuntimeException
     */
    @Test
    public void testFindById_ConIdInexistente_DeberiaLanzarExcepcion() {
        // Arrange
        Integer idInexistente = 999;
        when(pacienteRepository.findById(idInexistente)).thenReturn(Optional.empty());
        
        // Act & Assert
        RuntimeException exception = assertThrows(RuntimeException.class, () -> {
            pacienteService.findById(idInexistente);
        });
        
        assertTrue(exception.getMessage().contains("Paciente no encontrado con ID: " + idInexistente), 
                   "El mensaje de error debe contener el ID");
        
        // Verificar que no se llama al mapper cuando no hay datos
        verify(pacienteRepository, times(1)).findById(idInexistente);
        verify(pacienteMapper, never()).toDto(any(Paciente.class));
    }
    
    /**
     * Prueba unitaria para el método findByRut()
     * 
     * Verifica que:
     * 1. Se llame al repositorio con el RUT correcto
     * 2. Se mapeé el paciente encontrado a DTO
     * 3. Se retorne el PacienteDto correcto
     * 
     * Escenario: Búsqueda exitosa de un paciente por RUT existente
     * Entrada: RUT = "12345678-9"
     * Salida esperada: PacienteDto con los datos del paciente
     */
    @Test
    public void testFindByRut_ConRutExistente_DeberiaRetornarPaciente() {
        // Arrange
        String rutBuscado = "12345678-9";
        when(pacienteRepository.findByRut(rutBuscado)).thenReturn(Optional.of(pacienteEjemplo));
        when(pacienteMapper.toDto(pacienteEjemplo)).thenReturn(pacienteDtoEjemplo);
        
        // Act
        PacienteDto resultado = pacienteService.findByRut(rutBuscado);
        
        // Assert
        assertNotNull(resultado, "El resultado no debe ser null");
        assertEquals(pacienteDtoEjemplo.getRut(), resultado.getRut(), "El RUT debe coincidir");
        assertEquals(pacienteDtoEjemplo.getNombre(), resultado.getNombre(), "El nombre debe coincidir");
        assertEquals(rutBuscado, resultado.getRut(), "El RUT debe ser el buscado");
        
        // Verificar interacciones con mocks
        verify(pacienteRepository, times(1)).findByRut(rutBuscado);
        verify(pacienteMapper, times(1)).toDto(pacienteEjemplo);
    }
    
    /**
     * Método que se ejecuta después de cada prueba
     * Limpia recursos si es necesario
     */
    @AfterEach
    public void tearDown() {
        // Limpiar recursos si es necesario
        reset(pacienteRepository, pacienteMapper);
    }
}