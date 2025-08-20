package com.bankaya.challenge.pokemon.cucumber;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.bankaya.challenge.pokemon.pokemonsoapservices.client.ApiClient;
import com.bankaya.challenge.pokemon.pokemonsoapservices.dto.AbilityObject;
import com.bankaya.challenge.pokemon.pokemonsoapservices.dto.PokemonAbilitiesDto;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class PokemonGetAbilitiesSteps {

	private PokemonAbilitiesDto resultListHabilidades;

	@Autowired
	private ApiClient apiClient;

	@Autowired
	private PokemonPrincipal world;

	// *******Escenario consulta Habilidades*******/////
	@When("consulto las habilidades del pokemon por nombre")
	public void consultarHabilidades() {
		try {
			resultListHabilidades = apiClient.getPokemonAbilities(world.getName());
		} catch (Exception e) {
			world.setCaughtException(e);
		}

	}

	@Then("el resultado debe regresar la siguiente lista de habilidades:")
	public void resultGetHabilidades(DataTable expectedData) {
		List<String> expectedList = expectedData.asList();
		assertEquals(resultListHabilidades.getAbilities().size(), expectedList.size());
		// Itera y compara campo por campo
		for (int i = 0; i < resultListHabilidades.getAbilities().size(); i++) {
			String habilidad = expectedList.get(i);
			AbilityObject actual = resultListHabilidades.getAbilities().get(i);

			assertEquals(habilidad, actual.getAbility().getName(), "El name no coincide en fila " + i);
		}
	}

}
