Feature: Get Pokemon information

	Scenario: Get Name of Pokemon
		Given un pokemon con nombre "pikachu"
		When consulto el nombre del pokemon por nombre
		Then el resultado debe contener el nombre del pokemon "pikachu" 
		
	Scenario: Get Pokemon Not found Exception 
		Given un pokemon con nombre "mario"
		When consulto el nombre del pokemon por nombre
		Then debo obtener una excepcion de tipo "PokemonNotFoundException" 