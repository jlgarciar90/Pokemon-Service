package com.bankaya.challenge.pokemon.pokemonsoapservices.client;

import java.io.IOException;
import java.lang.reflect.Type;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Duration;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.bankaya.challenge.pokemon.pokemonsoapservices.dto.PokemonAbilitiesDto;
import com.bankaya.challenge.pokemon.pokemonsoapservices.dto.PokemonExperienceDto;
import com.bankaya.challenge.pokemon.pokemonsoapservices.dto.PokemonIdDto;
import com.bankaya.challenge.pokemon.pokemonsoapservices.dto.PokemonItemsDto;
import com.bankaya.challenge.pokemon.pokemonsoapservices.dto.PokemonLocationsArea;
import com.bankaya.challenge.pokemon.pokemonsoapservices.dto.PokemonNameDto;
import com.bankaya.challenge.pokemon.pokemonsoapservices.exception.PokemonException;
import com.bankaya.challenge.pokemon.pokemonsoapservices.exception.PokemonNotFoundException;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;

@Component
public class ApiClient {
	
	private final HttpClient client;
	private final Gson json = new Gson();
	private final String url;

	@Autowired
	public ApiClient() {
		this("https://pokeapi.co/api/v2/pokemon/"
		    ,HttpClient.newBuilder().connectTimeout(Duration.ofSeconds(5)).build());
	}

	public ApiClient(String url, HttpClient httpClient) {
		this.url = url;
	    this.client = httpClient;
	}

	public PokemonAbilitiesDto getPokemonAbilities(String name){
		String response = sendGetRequest(url,name);
	    return json.fromJson(response, PokemonAbilitiesDto.class);
	}
	
	public PokemonExperienceDto getPokemonExperience(String name){
		String response = sendGetRequest(url,name);
	    return json.fromJson(response, PokemonExperienceDto.class);
	}
	
	public PokemonItemsDto getPokemonItems(String name){
		String response = sendGetRequest(url,name);
	    return json.fromJson(response, PokemonItemsDto.class);
	}
	
	public PokemonIdDto getPokemonId(String name) {
		String response = sendGetRequest(url,name);
	    return json.fromJson(response, PokemonIdDto.class);
	}
	
	public PokemonNameDto getPokemonName(String name) {
		String response = sendGetRequest(url,name);
	    return json.fromJson(response, PokemonNameDto.class);
	}
	
	public List<PokemonLocationsArea> getPokemonLocations(String name) {
		String response = sendGetRequest(url,name);
		JsonObject jsonObject = json.fromJson(response, JsonObject.class);
		String urlLocations = jsonObject.get("location_area_encounters").getAsString();
		response = sendGetRequest(urlLocations,null);
		Type listType = new TypeToken<List<PokemonLocationsArea>>() {}.getType();
	    return json.fromJson(response, listType);
	}
	
	public String sendGetRequest(String url, String name){
		String finalUrl = name != null ? url + name : url;
		HttpRequest request = HttpRequest.newBuilder()
		        .uri(URI.create(finalUrl)) 
		        .GET()
		        .build();
		try {
			HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
			switch (response.statusCode()) {
			case 200:
				return response.body();
			case 404:
				throw new PokemonNotFoundException("No se encontro información del pokemon: "+ name);
			default:
				throw new PokemonException("Error al consultar la información: " + response.statusCode(),response.statusCode());
			}
		} catch (IOException ex) {
			throw new PokemonException("Error al realizar el envio de la solicitud: "+ ex.getMessage(),500);
		} catch (InterruptedException ex) {
			Thread.currentThread().interrupt();
			throw new PokemonException("Error al realizar el envio de la solicitud: "+ ex.getMessage(),500);
		}
	}

	
	

}
