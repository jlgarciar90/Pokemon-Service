package com.bankaya.challenge.pokemon.pokemonsoapservices.dto;

import java.util.List;

import com.google.gson.annotations.SerializedName;

import lombok.Data;

@Data
public class PokemonItemsDto {
	
	@SerializedName("held_items")
	private List<ItemObject> items;

}
