package com.bankaya.challenge.pokemon.pokemonsoapservices.exception;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

class PokemonLoggerExceptionTest {

	@Test
	void testConstructorWithMessage() {
		String mensaje = "Mock error logueo request";
		PokemonLoggerException ex = new PokemonLoggerException(mensaje);
		assertEquals(mensaje, ex.getMessage());
	}

}
