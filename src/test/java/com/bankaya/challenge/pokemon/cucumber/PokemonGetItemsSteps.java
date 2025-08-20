package com.bankaya.challenge.pokemon.cucumber;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import com.bankaya.challenge.pokemon.pokemonsoapservices.client.ApiClient;
import com.bankaya.challenge.pokemon.pokemonsoapservices.dto.ItemObject;
import com.bankaya.challenge.pokemon.pokemonsoapservices.dto.PokemonItemsDto;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class PokemonGetItemsSteps {

	 private PokemonItemsDto resultListItems;

	@Autowired
	private ApiClient apiClient;

	@Autowired
	private PokemonPrincipal world;

	// *******Escenario consulta items*******/////
	@When("consulto los items del pokemon por nombre")
	public void consultarItems() {
		try {
			resultListItems = apiClient.getPokemonItems(world.getName());
		} catch (Exception e) {
			world.setCaughtException(e);
		}

	}

	@Then("el resultado debe ser una lista vacia de items:")
	public void resultGetItemsAreaEmpty() {
		assertTrue(resultListItems.getItems().isEmpty());
	}

	@Then("el resultado debe regresar la siguiente lista de items:")
	public void resultGetItems(DataTable expectedData) {
		List<Map<String, String>> expectedList = expectedData.asMaps(String.class, String.class);
		assertEquals(resultListItems.getItems().size(), expectedList.size());
		// Itera y compara campo por campo
		for (int i = 0; i < resultListItems.getItems().size(); i++) {
			Map<String, String> expected = expectedList.get(i);
			ItemObject actual = resultListItems.getItems().get(i);

			assertEquals(expected.get("name"), actual.getItem().getName(), "El name no coincide en fila " + i);
			assertEquals(expected.get("url"), actual.getItem().getUrl(), "El url no coincide en fila " + i);
		}
	}

}
