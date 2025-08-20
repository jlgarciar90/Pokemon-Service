package com.bankaya.challenge.pokemon.cucumber;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.springframework.beans.factory.annotation.Autowired;

import com.bankaya.challenge.pokemon.pokemonsoapservices.client.ApiClient;
import com.bankaya.challenge.pokemon.pokemonsoapservices.dto.PokemonIdDto;

import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class PokemonGetIdSteps {

	private PokemonIdDto result;

	@Autowired
	private ApiClient apiClient;

	@Autowired
	private PokemonPrincipal world;

	// *******Escenario consulta id*******/////
	@When("consulto el id del pokemon por nombre")
	public void consultarId() {
		try {
			result = apiClient.getPokemonId(world.getName());
		} catch (Exception e) {
			world.setCaughtException(e);
		}

	}

	@Then("el resultado debe contener el identificador del pokemon {int}")
	public void resultGetId(int idEsperado) {
		assertEquals(idEsperado, result.getId());
	}

}
