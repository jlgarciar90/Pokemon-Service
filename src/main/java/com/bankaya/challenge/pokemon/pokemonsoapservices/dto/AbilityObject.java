package com.bankaya.challenge.pokemon.pokemonsoapservices.dto;

import lombok.Data;

@Data
public class AbilityObject {
	
	private Ability ability;
	
	@Data
	public static class Ability{
		private String name;
		private String url;
	}

}
