package com.bankaya.challenge.pokemon.pokemonsoapservices.exception;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

class PokemonExceptionTest {

	@Test
	void testConstructorWithMessage() {
		String mensaje = "Mock error implemented";
		PokemonException ex = new PokemonException(mensaje);
		assertEquals(mensaje, ex.getMessage());
		assertEquals(0, ex.getStatusCode());
	}
	
	@Test
	void testConstructorWithMessageAndStatus() {
		String mensaje = "Mock error implemented";
		int code = 400;
		PokemonException ex = new PokemonException(mensaje,code);
		assertEquals(mensaje, ex.getMessage());
		assertEquals(code, ex.getStatusCode());
	}

}
