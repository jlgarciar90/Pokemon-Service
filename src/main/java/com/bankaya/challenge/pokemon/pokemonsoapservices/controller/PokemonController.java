package com.bankaya.challenge.pokemon.pokemonsoapservices.controller;


import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

import com.bankaya.challenge.pokemon.pokemonsoapservices.service.PokemonServices;
import com.bankaya.challenge.pokemon.pokemonsoapservices.soap.AbilitiesRequest;
import com.bankaya.challenge.pokemon.pokemonsoapservices.soap.AbilitiesResponse;
import com.bankaya.challenge.pokemon.pokemonsoapservices.soap.BaseExperienceRequest;
import com.bankaya.challenge.pokemon.pokemonsoapservices.soap.BaseExperienceResponse;
import com.bankaya.challenge.pokemon.pokemonsoapservices.soap.GetIdRequest;
import com.bankaya.challenge.pokemon.pokemonsoapservices.soap.GetIdResponse;
import com.bankaya.challenge.pokemon.pokemonsoapservices.soap.GetNameRequest;
import com.bankaya.challenge.pokemon.pokemonsoapservices.soap.GetNameResponse;
import com.bankaya.challenge.pokemon.pokemonsoapservices.soap.HeldItemsRequest;
import com.bankaya.challenge.pokemon.pokemonsoapservices.soap.HeldItemsResponse;
import com.bankaya.challenge.pokemon.pokemonsoapservices.soap.LocationAreaRequest;
import com.bankaya.challenge.pokemon.pokemonsoapservices.soap.LocationAreaResponse;

@Endpoint
public class PokemonController {
	
	private static final String NAMESPACE = "http://challengeBankaya.com/pokemon/soap";
	 
	private final PokemonServices services;
	
	public PokemonController(PokemonServices serv) {
		this.services = serv;
	}

	@PayloadRoot(namespace = NAMESPACE, localPart = "AbilitiesRequest")
    @ResponsePayload
    public AbilitiesResponse getAbilities(@RequestPayload AbilitiesRequest request) {
        AbilitiesResponse response = new AbilitiesResponse();
        response.getAbilities().addAll(services.getAbilities(request.getName()));
        return response;
    }
	
	@PayloadRoot(namespace = NAMESPACE, localPart = "BaseExperienceRequest")
    @ResponsePayload
    public BaseExperienceResponse getExperience(@RequestPayload BaseExperienceRequest request) {
		BaseExperienceResponse response = new BaseExperienceResponse();
        response.setExperience(services.getPokemonExperience(request.getName()).getExperience());
        return response;
    }
	
	@PayloadRoot(namespace = NAMESPACE, localPart = "HeldItemsRequest")
    @ResponsePayload
    public HeldItemsResponse getItems(@RequestPayload HeldItemsRequest request) {
		HeldItemsResponse response = new HeldItemsResponse();
		response.getItem().addAll(services.getPokemonItems(request.getName()));
		return response;
    }
	
	@PayloadRoot(namespace = NAMESPACE, localPart = "getIdRequest")
    @ResponsePayload
    public GetIdResponse getExperience(@RequestPayload GetIdRequest request) {
		GetIdResponse response = new GetIdResponse();
        response.setId(services.getPokemonId(request.getName()).getId());
        return response;
    }
	
	@PayloadRoot(namespace = NAMESPACE, localPart = "getNameRequest")
    @ResponsePayload
    public GetNameResponse getName(@RequestPayload GetNameRequest request) {
		GetNameResponse response = new GetNameResponse();
        response.setName(services.getPokemonName(request.getName()).getName());
        return response;
    }
	
	@PayloadRoot(namespace = NAMESPACE, localPart = "LocationAreaRequest")
    @ResponsePayload
    public LocationAreaResponse getLocationsArea(@RequestPayload LocationAreaRequest request) {
		LocationAreaResponse response = new LocationAreaResponse();
        response.getArea().addAll(services.getPokemonLocationsArea(request.getName()));
        return response;
    }
	
	
}
