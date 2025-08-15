package com.bankaya.challenge.pokemon.pokemonsoapservices.dto;

import com.google.gson.annotations.SerializedName;

import lombok.Data;

@Data
public class PokemonExperienceDto {
	
	@SerializedName("base_experience")
	private int experience;

}
