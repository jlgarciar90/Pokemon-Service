Feature: Get Pokemon abilities 

	Scenario: Get abilities of Pokemon
		Given un pokemon con nombre "pikachu"
		When consulto las habilidades del pokemon por nombre
		Then el resultado debe regresar la siguiente lista de habilidades:
			| static |
			| lightning-rod |