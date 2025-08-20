package com.bankaya.challenge.pokemon.pokemonsoapservices.exception;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

class PokemonNotFoundExceptionTest {

	@Test
	void testConstructorWithMessage() {
		String mensaje = "Mock error Pokemon not found";
		PokemonNotFoundException ex = new PokemonNotFoundException(mensaje);
		assertEquals(mensaje, ex.getMessage());
	}

}
