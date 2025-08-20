Feature: Get Pokemon Items 

	Scenario: Get Items List empty of Pokemon
		Given un pokemon con nombre "venusaur"
		When consulto los items del pokemon por nombre
		Then el resultado debe ser una lista vacia de items:
		
		
	Scenario: Get Items of Pokemon
		Given un pokemon con nombre "pikachu"
		When consulto los items del pokemon por nombre
		Then el resultado debe regresar la siguiente lista de items:
			| name | url |
			| oran-berry | https://pokeapi.co/api/v2/item/132/ |
			| light-ball | https://pokeapi.co/api/v2/item/213/ |