package com.bankaya.challenge.pokemon.pokemonsoapservices.mapper;

import java.util.ArrayList;
import java.util.List;

import com.bankaya.challenge.pokemon.pokemonsoapservices.dto.PokemonItemsDto;
import com.bankaya.challenge.pokemon.pokemonsoapservices.dto.PokemonLocationsArea;
import com.bankaya.challenge.pokemon.pokemonsoapservices.exception.PokemonException;
import com.bankaya.challenge.pokemon.pokemonsoapservices.soap.Area;
import com.bankaya.challenge.pokemon.pokemonsoapservices.soap.Item;

public class PokemonMapper {
	
	private PokemonMapper() {
		super();
	}

	public static List<Item> mapToListItemResponse(PokemonItemsDto dto, String name) {
		List<Item> itemsResponse = new ArrayList<>();
		if (dto.getItems() != null && !dto.getItems().isEmpty()) {
          dto.getItems().stream()
                .map(obj -> {
                    Item soapItem = new Item();
                    soapItem.setName(obj.getItem().getName());
                    soapItem.setUrl(obj.getItem().getUrl());
                    return soapItem;
                })
                .forEach(itemsResponse::add);
        }else {
			throw new PokemonException("No se encontraron items para el pokemon: "+name);
		}

        return itemsResponse;
    }
	
	public static List<Area> mapToListLocationAreaResponse(List<PokemonLocationsArea> listAreasDto, String name) {
		List<Area> listAreaResponse = new ArrayList<>();
		if (listAreasDto != null && !listAreasDto.isEmpty()) {
			listAreasDto.stream()
                .map(obj -> {
                	Area soapArea = new Area();
                    soapArea.setName(obj.getArea().getName());
                    soapArea.setUrl(obj.getArea().getUrl());
                    return soapArea;
                })
                .forEach(listAreaResponse::add);
        }else {
			throw new PokemonException("No se encontraron areas para el pokemon: "+name);
		}

        return listAreaResponse;
    }

}
