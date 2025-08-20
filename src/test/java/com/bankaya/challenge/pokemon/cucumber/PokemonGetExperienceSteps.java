package com.bankaya.challenge.pokemon.cucumber;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.springframework.beans.factory.annotation.Autowired;

import com.bankaya.challenge.pokemon.pokemonsoapservices.client.ApiClient;
import com.bankaya.challenge.pokemon.pokemonsoapservices.dto.PokemonExperienceDto;

import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class PokemonGetExperienceSteps {

	private PokemonExperienceDto resultExperience;

	@Autowired
	private ApiClient apiClient;

	@Autowired
	private PokemonPrincipal world;

	// *******Escenario consulta experiencia*******/////
	@When("consulto la experiencia del pokemon por nombre")
	public void consultarExperiencia() {
		try {
			resultExperience = apiClient.getPokemonExperience(world.getName());
		} catch (Exception e) {
			world.setCaughtException(e);
		}

	}

	@Then("el resultado debe contener la experiencia del pokemon {int}")
	public void resultGetExperience(int expectedExperience) {
		assertEquals(expectedExperience, resultExperience.getExperience());
	}
}
