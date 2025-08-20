Feature: Get Pokemon experience 

	Scenario: Get experience of Pokemon
		Given un pokemon con nombre "pikachu"
		When consulto la experiencia del pokemon por nombre
		Then el resultado debe contener la experiencia del pokemon 112