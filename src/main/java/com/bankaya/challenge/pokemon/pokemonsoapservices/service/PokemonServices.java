package com.bankaya.challenge.pokemon.pokemonsoapservices.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.bankaya.challenge.pokemon.pokemonsoapservices.client.ApiClient;
import com.bankaya.challenge.pokemon.pokemonsoapservices.dto.ItemObject;
import com.bankaya.challenge.pokemon.pokemonsoapservices.dto.PokemonAbilitiesDto;
import com.bankaya.challenge.pokemon.pokemonsoapservices.dto.PokemonExperienceDto;
import com.bankaya.challenge.pokemon.pokemonsoapservices.dto.PokemonIdDto;
import com.bankaya.challenge.pokemon.pokemonsoapservices.dto.PokemonLocationsArea;
import com.bankaya.challenge.pokemon.pokemonsoapservices.dto.PokemonNameDto;
import com.bankaya.challenge.pokemon.pokemonsoapservices.exception.PokemonException;
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
		List<ItemObject> items = pokeApiClient.getPokemonItems(name).getItems();
		List<Item> itemsResponse;
		if (items!=null && !items.isEmpty()) {
			itemsResponse = new ArrayList<>();
			for (ItemObject itemObject : items) {
				Item soapItem = new Item();
                soapItem.setName(itemObject.getItem().getName());
                soapItem.setUrl(itemObject.getItem().getUrl());
                itemsResponse.add(soapItem);
			}
			return itemsResponse;
		}else {
			throw new PokemonException("No se encontraron items para el pokemon: "+name);
		}
		
	}

	public PokemonIdDto getPokemonId(String name) {
		return pokeApiClient.getPokemonId(name);
	}

	public PokemonNameDto getPokemonName(String name) {
		return pokeApiClient.getPokemonName(name);
	}

	public List<Area> getPokemonLocationsArea(String name) {
		List<PokemonLocationsArea> itemsArea = pokeApiClient.getPokemonLocations(name);
		List<Area> itemsAreaResponse;
		if (itemsArea!=null && !itemsArea.isEmpty()) {
			itemsAreaResponse = new ArrayList<>();
			for (PokemonLocationsArea area : itemsArea) {
				Area soapItem = new Area();
                soapItem.setName(area.getArea().getName());
                soapItem.setUrl(area.getArea().getUrl());
                itemsAreaResponse.add(soapItem);
			}
			return itemsAreaResponse;
		}else {
			throw new PokemonException("No se encontraron areas disponibles para el pokemon: "+name);
		}
	}

}
