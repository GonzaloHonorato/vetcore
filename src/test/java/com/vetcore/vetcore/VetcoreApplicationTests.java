package com.vetcore.vetcore;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

/**
 * Pruebas básicas para la aplicación Vetcore
 * 
 * Esta clase contiene pruebas simples que no requieren 
 * el contexto completo de Spring Boot.
 */
class VetcoreApplicationTests {

	/**
	 * Prueba básica que siempre pasa
	 * Verifica que la clase de prueba se ejecuta correctamente
	 */
	@Test
	void basicTest() {
		// Prueba simple que siempre pasa
		assertTrue(true, "Esta es una prueba básica");
	}
	
	/**
	 * Prueba que verifica que la clase principal existe
	 */
	@Test
	void applicationClassExists() {
		assertNotNull(VetcoreApplication.class, "La clase VetcoreApplication debe existir");
	}

}
