package com.bankaya.challenge.pokemon.pokemonsoapservices.exception;

public class PokemonException extends RuntimeException {
	
	private int statusCode;
	
	public PokemonException(String message) {
		super(message);
	}

	public PokemonException(String message, int statusCode) {
		super(message);
		this.statusCode =statusCode;
	}

	public int getStatusCode() {
        return statusCode;
    }
}
