package com.bankaya.challenge.pokemon.pokemonsoapservices.dto;

import com.google.gson.annotations.SerializedName;

import lombok.Data;

@Data
public class PokemonLocationsArea {
	
	@SerializedName("location_area")
	private Area area;
	
	@Data
	public static class Area{
		private String name;
		private String url;
	}

}
