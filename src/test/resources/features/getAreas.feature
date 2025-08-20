Feature: Get Pokemon areas 

	Scenario: Get areas List empty of Pokemon
		Given un pokemon con nombre "venusaur"
		When consulto las areas del pokemon por nombre
		Then el resultado debe ser una lista vacia de areas:
		
		
	Scenario: Get areas of Pokemon
		Given un pokemon con nombre "charmander"
		When consulto las areas del pokemon por nombre
		Then el resultado debe regresar la siguiente lista de areas:
			| name | url |
			| pallet-town-area | https://pokeapi.co/api/v2/location-area/285/ |
			| kanto-route-24-area | https://pokeapi.co/api/v2/location-area/314/ |
			| lumiose-city-area | https://pokeapi.co/api/v2/location-area/779/ |
			| alola-route-3-main | https://pokeapi.co/api/v2/location-area/1043/ |