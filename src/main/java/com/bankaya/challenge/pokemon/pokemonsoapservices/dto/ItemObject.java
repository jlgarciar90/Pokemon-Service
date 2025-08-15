package com.bankaya.challenge.pokemon.pokemonsoapservices.dto;

import lombok.Data;

@Data
public class ItemObject {
	
	private Item item;
	
	@Data
	public static class Item{
		private String name;
		private String url;
	}

}
