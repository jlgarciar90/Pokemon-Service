package com.bankaya.challenge.pokemon.cucumber;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import com.bankaya.challenge.pokemon.pokemonsoapservices.client.ApiClient;
import com.bankaya.challenge.pokemon.pokemonsoapservices.dto.PokemonLocationsArea;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class PokemonGetLocationAreasSteps {

	private List<PokemonLocationsArea> resultListAreas;

	@Autowired
	private ApiClient apiClient;

	@Autowired
	private PokemonPrincipal world;

	// *******Escenario consulta Location areas*******/////
	@When("consulto las areas del pokemon por nombre")
	public void consultarLocationsArea() {
		try {
			resultListAreas = apiClient.getPokemonLocations(world.getName());
		} catch (Exception e) {
			world.setCaughtException(e);
		}

	}

	@Then("el resultado debe ser una lista vacia de areas:")
	public void resultGetLocationAreaEmpty() {
		assertTrue(resultListAreas.isEmpty());
	}

	@Then("el resultado debe regresar la siguiente lista de areas:")
	public void resultGetLocationAreas(DataTable expectedData) {
		List<Map<String, String>> expectedList = expectedData.asMaps(String.class, String.class);
		assertEquals(resultListAreas.size(), expectedList.size());
		// Itera y compara campo por campo
		for (int i = 0; i < resultListAreas.size(); i++) {
			Map<String, String> expected = expectedList.get(i);
			PokemonLocationsArea actual = resultListAreas.get(i);

			assertEquals(expected.get("name"), actual.getArea().getName(), "El name no coincide en fila " + i);
			assertEquals(expected.get("url"), actual.getArea().getUrl(), "El url no coincide en fila " + i);
		}
	}

}
