package com.bankaya.challenge.pokemon.pokemonsoapservices.client;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.io.IOException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.bankaya.challenge.pokemon.pokemonsoapservices.dto.PokemonAbilitiesDto;
import com.bankaya.challenge.pokemon.pokemonsoapservices.dto.PokemonExperienceDto;
import com.bankaya.challenge.pokemon.pokemonsoapservices.dto.PokemonIdDto;
import com.bankaya.challenge.pokemon.pokemonsoapservices.dto.PokemonItemsDto;
import com.bankaya.challenge.pokemon.pokemonsoapservices.dto.PokemonLocationsArea;
import com.bankaya.challenge.pokemon.pokemonsoapservices.dto.PokemonNameDto;

class ApiClientTest {

	private HttpClient httpClient;
	private HttpResponse<String> httpResponse;
	private ApiClient apiClient;

	@BeforeEach
	void setup() {
		httpClient = mock(HttpClient.class);
		httpResponse = mock(HttpResponse.class);
		apiClient = new ApiClient("https://pokeapi.co/api/v2/pokemon/", httpClient);
	}

	@Test
	void testGetPokemonAbilities() throws IOException, InterruptedException {
		String mockBody = "{\"abilities\" :"
				+ "[{\"ability\":{\"name\":\"pressure\",\"url\":\"https://pokeapi.co/api/v2/ability/46/\"}"
				+ ",\"is_hidden\":false,\"slot\":1},{\"ability\":{\"name\":\"unnerve\""
				+ ",\"url\":\"https://pokeapi.co/api/v2/ability/127/\"},\"is_hidden\":true,\"slot\":3}]"
				+ ",\"base_experience\":306}";

		when(httpClient.send(any(HttpRequest.class), any(HttpResponse.BodyHandler.class))).thenReturn(httpResponse);
		when(httpResponse.statusCode()).thenReturn(200);
		when(httpResponse.body()).thenReturn(mockBody);

		PokemonAbilitiesDto dto = apiClient.getPokemonAbilities("pikachu");

		assertEquals("pressure", dto.getAbilities().get(0).getAbility().getName());
		assertEquals("https://pokeapi.co/api/v2/ability/46/", dto.getAbilities().get(0).getAbility().getUrl());
	}

	@Test
	void testGetPokemonExperience() throws IOException, InterruptedException {
		String mockBody = "{\"abilities\" :"
				+ "[{\"ability\":{\"name\":\"pressure\",\"url\":\"https://pokeapi.co/api/v2/ability/46/\"}"
				+ ",\"is_hidden\":false,\"slot\":1},{\"ability\":{\"name\":\"unnerve\""
				+ ",\"url\":\"https://pokeapi.co/api/v2/ability/127/\"},\"is_hidden\":true,\"slot\":3}]"
				+ ",\"base_experience\":306}";

		when(httpClient.send(any(HttpRequest.class), any(HttpResponse.BodyHandler.class))).thenReturn(httpResponse);
		when(httpResponse.statusCode()).thenReturn(200);
		when(httpResponse.body()).thenReturn(mockBody);

		PokemonExperienceDto dto = apiClient.getPokemonExperience("pikachu");

		assertEquals(306, dto.getExperience());
	}

	@Test
	void testGetPokemonItems() throws IOException, InterruptedException {
		String mockBody = "{\"height\":4,"
				+ "\"held_items\":[{\"item\":{\"name\":\"oran-berry\",\"url\":\"https://pokeapi.co/api/v2/item/132/\"}},"
				+ "{\"item\":{\"name\":\"light-ball\",\"url\":\"https://pokeapi.co/api/v2/item/213/\"}}],"
				+ "\"id\":25," + "\"is_default\":true,"
				+ "\"location_area_encounters\":\"https://pokeapi.co/api/v2/pokemon/25/encounters\"}";

		when(httpClient.send(any(HttpRequest.class), any(HttpResponse.BodyHandler.class))).thenReturn(httpResponse);
		when(httpResponse.statusCode()).thenReturn(200);
		when(httpResponse.body()).thenReturn(mockBody);

		PokemonItemsDto dto = apiClient.getPokemonItems("pikachu");

		assertEquals("oran-berry", dto.getItems().get(0).getItem().getName());
		assertEquals("https://pokeapi.co/api/v2/item/132/", dto.getItems().get(0).getItem().getUrl());
		
		assertEquals("light-ball", dto.getItems().get(1).getItem().getName());
		assertEquals("https://pokeapi.co/api/v2/item/213/", dto.getItems().get(1).getItem().getUrl());
		
	}
	
	@Test
	void testGetPokemonId() throws IOException, InterruptedException {
		String mockBody = "{\"height\":4"
				+ ",\"held_items\":[{\"item\":{\"name\":\"oran-berry\",\"url\":\"https://pokeapi.co/api/v2/item/132/\"}},"
				+ "{\"item\":{\"name\":\"light-ball\",\"url\":\"https://pokeapi.co/api/v2/item/213/\"}}],"
				+ "\"id\":25,\"is_default\":true,\"location_area_encounters\":\"https://pokeapi.co/api/v2/pokemon/25/encounters\",\"name\":\"pikachu\"}";

		when(httpClient.send(any(HttpRequest.class), any(HttpResponse.BodyHandler.class))).thenReturn(httpResponse);
		when(httpResponse.statusCode()).thenReturn(200);
		when(httpResponse.body()).thenReturn(mockBody);

		PokemonIdDto dto = apiClient.getPokemonId("pikachu");

		assertEquals(25, dto.getId());
	}
	
	@Test
	void testGetPokemonName() throws IOException, InterruptedException {
		String mockBody = "{\"height\":4"
				+ ",\"held_items\":[{\"item\":{\"name\":\"oran-berry\",\"url\":\"https://pokeapi.co/api/v2/item/132/\"}},"
				+ "{\"item\":{\"name\":\"light-ball\",\"url\":\"https://pokeapi.co/api/v2/item/213/\"}}],"
				+ "\"id\":25,\"is_default\":true,\"location_area_encounters\":\"https://pokeapi.co/api/v2/pokemon/25/encounters\",\"name\":\"pikachu\"}";

		when(httpClient.send(any(HttpRequest.class), any(HttpResponse.BodyHandler.class))).thenReturn(httpResponse);
		when(httpResponse.statusCode()).thenReturn(200);
		when(httpResponse.body()).thenReturn(mockBody);

		PokemonNameDto dto = apiClient.getPokemonName("pikachu");

		assertEquals("pikachu", dto.getName());
	}
	
	@Test
	void testGetPokemonLocations() throws IOException, InterruptedException {
		String mockBody1 = "{\"height\":4,"
				+ "\"held_items\":[{\"item\":{\"name\":\"oran-berry\",\"url\":\"https://pokeapi.co/api/v2/item/132/\"}},"
				+ "{\"item\":{\"name\":\"light-ball\",\"url\":\"https://pokeapi.co/api/v2/item/213/\"}}],"
				+ "\"id\":25," + "\"is_default\":true,"
				+ "\"location_area_encounters\":\"https://pokeapi.co/api/v2/pokemon/25/encounters\"}";
		String mockBody2 = "[{\"location_area\":{\"name\":\"trophy-garden-area\",\"url\":\"https://pokeapi.co/api/v2/location-area/118/\"}}"
				+ ",{\"location_area\":{\"name\":\"pallet-town-area\",\"url\":\"https://pokeapi.co/api/v2/location-area/285/\"}},"
				+ "{\"location_area\":{\"name\":\"kanto-route-2-south-towards-viridian-city\",\"url\":\"https://pokeapi.co/api/v2/location-area/296/\"}}]";

		when(httpClient.send(any(HttpRequest.class), any(HttpResponse.BodyHandler.class))).thenReturn(httpResponse);
		when(httpResponse.statusCode()).thenReturn(200);
		when(httpResponse.body()).thenReturn(mockBody1).thenReturn(mockBody2);

		List<PokemonLocationsArea> listDto = apiClient.getPokemonLocations("pikachu");
		assertEquals(3, listDto.size());
		assertEquals("trophy-garden-area", listDto.get(0).getArea().getName());
		assertEquals("https://pokeapi.co/api/v2/location-area/118/", listDto.get(0).getArea().getUrl());
		
		assertEquals("kanto-route-2-south-towards-viridian-city", listDto.get(2).getArea().getName());
		assertEquals("https://pokeapi.co/api/v2/location-area/296/", listDto.get(2).getArea().getUrl());
		
	}

}
