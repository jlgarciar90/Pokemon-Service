package com.bankaya.challenge.pokemon.cucumber;

import org.springframework.boot.test.context.SpringBootTest;

import com.bankaya.challenge.pokemon.PokemonSoapServicesApplication;

import io.cucumber.spring.CucumberContextConfiguration;

@CucumberContextConfiguration
@SpringBootTest(classes = PokemonSoapServicesApplication.class)
public class CucumberSpringConfiguration {
	// Clase vacía, solo para integrar Cucumber con Spring Boot
}
