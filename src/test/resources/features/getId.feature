Feature: Get Pokemon Id 

	Scenario: Get Id of Pokemon
		Given un pokemon con nombre "pikachu"
		When consulto el id del pokemon por nombre
		Then el resultado debe contener el identificador del pokemon 25