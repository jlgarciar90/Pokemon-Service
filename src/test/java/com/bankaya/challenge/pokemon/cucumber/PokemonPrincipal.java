package com.bankaya.challenge.pokemon.cucumber;

import org.springframework.stereotype.Component;

import io.cucumber.spring.ScenarioScope;

@Component
@ScenarioScope
public class PokemonPrincipal {
	
	private String name;
	private Exception caughtException;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Exception getCaughtException() {
		return caughtException;
	}
	public void setCaughtException(Exception caughtException) {
		this.caughtException = caughtException;
	}
	
	

}
