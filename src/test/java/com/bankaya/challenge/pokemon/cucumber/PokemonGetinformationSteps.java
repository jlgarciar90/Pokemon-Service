package com.bankaya.challenge.pokemon.cucumber;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.springframework.beans.factory.annotation.Autowired;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;

public class PokemonGetinformationSteps {
	
	@Autowired
	private PokemonPrincipal pokemonName;
	 
    //*******Given para todos los escenarios*******/////
	@Given("un pokemon con nombre {string}")
	public void elPokemon(String name) {
		pokemonName.setName(name);
	}
	
	//*******Escenario Pokemon Not found*******/////
	@Then("debo obtener una excepcion de tipo {string}")
    public void resultadoPokemonIdNotFoundException(String nombreException) {
        assertNotNull(pokemonName.getCaughtException(), "Se esperaba una excepción, pero no se lanzó ninguna.");
        assertEquals(pokemonName.getCaughtException().getClass().getSimpleName(),nombreException);
	}
	
	
	
	
}
