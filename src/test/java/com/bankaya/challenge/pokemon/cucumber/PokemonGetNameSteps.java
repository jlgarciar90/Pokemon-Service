package com.bankaya.challenge.pokemon.cucumber;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.springframework.beans.factory.annotation.Autowired;

import com.bankaya.challenge.pokemon.pokemonsoapservices.client.ApiClient;
import com.bankaya.challenge.pokemon.pokemonsoapservices.dto.PokemonNameDto;

import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class PokemonGetNameSteps {

	private PokemonNameDto resultName;

	@Autowired
	private ApiClient apiClient;
	
	@Autowired
    private PokemonPrincipal world;

	// *******Escenario consulta nombre*******/////
	@When("consulto el nombre del pokemon por nombre")
	public void consultarName() {
		try {
			resultName = apiClient.getPokemonName(world.getName());
		} catch (Exception e) {
			 world.setCaughtException(e);
		}

	}

	@Then("el resultado debe contener el nombre del pokemon {string}")
	public void resultGetName(String name) {
		assertEquals(name, resultName.getName());
	}

}
