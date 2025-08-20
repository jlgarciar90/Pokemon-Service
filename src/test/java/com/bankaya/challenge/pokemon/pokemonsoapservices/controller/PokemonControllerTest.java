package com.bankaya.challenge.pokemon.pokemonsoapservices.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.bankaya.challenge.pokemon.pokemonsoapservices.dto.PokemonExperienceDto;
import com.bankaya.challenge.pokemon.pokemonsoapservices.dto.PokemonIdDto;
import com.bankaya.challenge.pokemon.pokemonsoapservices.dto.PokemonNameDto;
import com.bankaya.challenge.pokemon.pokemonsoapservices.service.PokemonServices;
import com.bankaya.challenge.pokemon.pokemonsoapservices.soap.AbilitiesRequest;
import com.bankaya.challenge.pokemon.pokemonsoapservices.soap.AbilitiesResponse;
import com.bankaya.challenge.pokemon.pokemonsoapservices.soap.Area;
import com.bankaya.challenge.pokemon.pokemonsoapservices.soap.BaseExperienceRequest;
import com.bankaya.challenge.pokemon.pokemonsoapservices.soap.BaseExperienceResponse;
import com.bankaya.challenge.pokemon.pokemonsoapservices.soap.GetIdRequest;
import com.bankaya.challenge.pokemon.pokemonsoapservices.soap.GetIdResponse;
import com.bankaya.challenge.pokemon.pokemonsoapservices.soap.GetNameRequest;
import com.bankaya.challenge.pokemon.pokemonsoapservices.soap.GetNameResponse;
import com.bankaya.challenge.pokemon.pokemonsoapservices.soap.HeldItemsRequest;
import com.bankaya.challenge.pokemon.pokemonsoapservices.soap.HeldItemsResponse;
import com.bankaya.challenge.pokemon.pokemonsoapservices.soap.Item;
import com.bankaya.challenge.pokemon.pokemonsoapservices.soap.LocationAreaRequest;
import com.bankaya.challenge.pokemon.pokemonsoapservices.soap.LocationAreaResponse;

class PokemonControllerTest {
	
	@Mock
    private PokemonServices services;

    @InjectMocks
    private PokemonController controller;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetAbilities() {
        AbilitiesRequest request = new AbilitiesRequest();
        request.setName("pikachu");

        List<String> mockAbilities = Arrays.asList("static", "lightning-rod");
        when(services.getAbilities("pikachu")).thenReturn(mockAbilities);

        AbilitiesResponse response = controller.getAbilities(request);

        assertEquals(2, response.getAbilities().size());
        assertEquals("static", response.getAbilities().get(0));
    }

    @Test
    void testGetExperience() {
        BaseExperienceRequest request = new BaseExperienceRequest();
        request.setName("pikachu");

        PokemonExperienceDto dto = new PokemonExperienceDto();
        dto.setExperience(112);

        when(services.getPokemonExperience("pikachu")).thenReturn(dto);

        BaseExperienceResponse response = controller.getExperience(request);

        assertEquals(112, response.getExperience());
    }

    @Test
    void testGetItems() {
        HeldItemsRequest request = new HeldItemsRequest();
        request.setName("pikachu");
        Item item1 = new Item();
        item1.setName("oran-berry");
        item1.setUrl("http://item.com");
        
        Item item2 = new Item();
        item2.setName("sitrus-berry");
        item2.setUrl("http://item2.com");
        List<Item> items = Arrays.asList(item1,item2 );
        when(services.getPokemonItems("pikachu")).thenReturn(items);

        HeldItemsResponse response = controller.getItems(request);

        assertEquals(2, response.getItem().size());
        assertEquals("oran-berry", response.getItem().get(0).getName());
        assertEquals("http://item.com", response.getItem().get(0).getUrl());
    }

    @Test
    void testGetId() {
        GetIdRequest request = new GetIdRequest();
        request.setName("pikachu");

        PokemonIdDto dto = new PokemonIdDto();
        dto.setId(25);

        when(services.getPokemonId("pikachu")).thenReturn(dto);

        GetIdResponse response = controller.getExperience(request);

        assertEquals(25, response.getId());
    }

    @Test
    void testGetName() {
        GetNameRequest request = new GetNameRequest();
        request.setName("pikachu");

        PokemonNameDto dto = new PokemonNameDto();
        dto.setName("pikachu");

        when(services.getPokemonName("pikachu")).thenReturn(dto);

        GetNameResponse response = controller.getName(request);

        assertEquals("pikachu", response.getName());
    }

    @Test
    void testGetLocationsArea() {
        LocationAreaRequest request = new LocationAreaRequest();
        request.setName("pikachu");
        Area area1 = new Area();
        area1.setName("kanto-route-2");
        area1.setUrl("http://area1.com");
        Area area2 = new Area();
        area2.setName("kanto-route-2");
        area2.setUrl("http://area1.com");
        List<Area> areas = Arrays.asList(area1,area2);
        when(services.getPokemonLocationsArea("pikachu")).thenReturn(areas);

        LocationAreaResponse response = controller.getLocationsArea(request);

        assertEquals(2, response.getArea().size());
        assertEquals("kanto-route-2", response.getArea().get(0).getName());
        assertEquals("http://area1.com", response.getArea().get(0).getUrl());
    }

}
