package com.bankaya.challenge.pokemon.pokemonsoapservices.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.bankaya.challenge.pokemon.pokemonsoapservices.client.ApiClient;
import com.bankaya.challenge.pokemon.pokemonsoapservices.dto.PokemonAbilitiesDto;
import com.bankaya.challenge.pokemon.pokemonsoapservices.dto.PokemonExperienceDto;
import com.bankaya.challenge.pokemon.pokemonsoapservices.dto.PokemonIdDto;
import com.bankaya.challenge.pokemon.pokemonsoapservices.dto.PokemonLocationsArea;
import com.bankaya.challenge.pokemon.pokemonsoapservices.dto.PokemonNameDto;
import com.bankaya.challenge.pokemon.pokemonsoapservices.mapper.PokemonMapper;
import com.bankaya.challenge.pokemon.pokemonsoapservices.soap.Area;
import com.bankaya.challenge.pokemon.pokemonsoapservices.soap.Item;

@Service
public class PokemonServices {

	private ApiClient pokeApiClient;

	public PokemonServices(ApiClient cliente) {
		this.pokeApiClient = cliente;
	}

	public List<String> getAbilities(String name) {
		PokemonAbilitiesDto pokemonAbilities = pokeApiClient.getPokemonAbilities(name);
		return pokemonAbilities.getAbilities().stream().map(i -> i.getAbility().getName()).toList();
	}

	public PokemonExperienceDto getPokemonExperience(String name) {
		return pokeApiClient.getPokemonExperience(name);
	}

	public List<Item> getPokemonItems(String name) {
		return PokemonMapper.mapToListItemResponse(pokeApiClient.getPokemonItems(name), name);
	}

	public PokemonIdDto getPokemonId(String name) {
		return pokeApiClient.getPokemonId(name);
	}

	public PokemonNameDto getPokemonName(String name) {
		return pokeApiClient.getPokemonName(name);
	}

	public List<Area> getPokemonLocationsArea(String name) {
		List<PokemonLocationsArea> itemsArea = pokeApiClient.getPokemonLocations(name);
		return PokemonMapper.mapToListLocationAreaResponse(itemsArea, name);
	}

}
