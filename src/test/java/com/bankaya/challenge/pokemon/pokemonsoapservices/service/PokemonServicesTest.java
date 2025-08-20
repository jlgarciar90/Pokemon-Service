package com.bankaya.challenge.pokemon.pokemonsoapservices.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.mockStatic;
import static org.mockito.Mockito.when;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;
import org.mockito.MockedStatic;

import com.bankaya.challenge.pokemon.pokemonsoapservices.client.ApiClient;
import com.bankaya.challenge.pokemon.pokemonsoapservices.dto.AbilityObject;
import com.bankaya.challenge.pokemon.pokemonsoapservices.dto.AbilityObject.Ability;
import com.bankaya.challenge.pokemon.pokemonsoapservices.dto.PokemonAbilitiesDto;
import com.bankaya.challenge.pokemon.pokemonsoapservices.dto.PokemonExperienceDto;
import com.bankaya.challenge.pokemon.pokemonsoapservices.dto.PokemonIdDto;
import com.bankaya.challenge.pokemon.pokemonsoapservices.dto.PokemonItemsDto;
import com.bankaya.challenge.pokemon.pokemonsoapservices.dto.PokemonLocationsArea;
import com.bankaya.challenge.pokemon.pokemonsoapservices.dto.PokemonNameDto;
import com.bankaya.challenge.pokemon.pokemonsoapservices.exception.PokemonException;
import com.bankaya.challenge.pokemon.pokemonsoapservices.mapper.PokemonMapper;
import com.bankaya.challenge.pokemon.pokemonsoapservices.soap.Area;
import com.bankaya.challenge.pokemon.pokemonsoapservices.soap.Item;

class PokemonServicesTest {

    private ApiClient apiClient;
    private PokemonServices pokemonServices;

    @BeforeEach
    void setUp() {
        apiClient = mock(ApiClient.class);
        pokemonServices = new PokemonServices(apiClient);
    }

    @Test
    void testGetAbilities() {
        // Arrange
    	Ability abilityDto = mock(Ability.class);
        when(abilityDto.getName()).thenReturn("static");

        AbilityObject wrapper = mock(AbilityObject.class);
        when(wrapper.getAbility()).thenReturn(abilityDto);

        PokemonAbilitiesDto abilitiesDto = mock(PokemonAbilitiesDto.class);
        when(abilitiesDto.getAbilities()).thenReturn(List.of(wrapper));

        when(apiClient.getPokemonAbilities("pikachu")).thenReturn(abilitiesDto);

        List<String> result = pokemonServices.getAbilities("pikachu");

        // Assert
        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals("static", result.get(0));
    }

    @Test
    void testGetPokemonExperience() {
        // Arrange
        PokemonExperienceDto expDto = mock(PokemonExperienceDto.class);
        when(apiClient.getPokemonExperience("charmander")).thenReturn(expDto);

        // Act
        PokemonExperienceDto result = pokemonServices.getPokemonExperience("charmander");

        // Assert
        assertNotNull(result);
        assertEquals(expDto, result);
    }

    @Test
    void testGetPokemonItems() {
        // Arrange
        PokemonItemsDto itemsDto = mock(PokemonItemsDto.class);
        when(apiClient.getPokemonItems("bulbasaur")).thenReturn(itemsDto);

        Item soapItem = new Item();
        soapItem.setName("potion");
        soapItem.setUrl("url");

        try (MockedStatic<PokemonMapper> mockedMapper = mockStatic(PokemonMapper.class)) {
            mockedMapper.when(() -> PokemonMapper.mapToListItemResponse(itemsDto, "bulbasaur"))
                        .thenReturn(List.of(soapItem));

            // Act
            List<Item> result = pokemonServices.getPokemonItems("bulbasaur");

            // Assert
            assertEquals(1, result.size());
            assertEquals("potion", result.get(0).getName());
        }
    }

    @Test
    void testGetPokemonId() {
        // Arrange
        PokemonIdDto idDto = mock(PokemonIdDto.class);
        when(apiClient.getPokemonId("mewtwo")).thenReturn(idDto);

        // Act
        PokemonIdDto result = pokemonServices.getPokemonId("mewtwo");

        // Assert
        assertNotNull(result);
        assertEquals(idDto, result);
    }

    @Test
    void testGetPokemonName() {
        // Arrange
        PokemonNameDto nameDto = mock(PokemonNameDto.class);
        when(apiClient.getPokemonName("snorlax")).thenReturn(nameDto);

        // Act
        PokemonNameDto result = pokemonServices.getPokemonName("snorlax");

        // Assert
        assertNotNull(result);
        assertEquals(nameDto, result);
    }

    @Test
    void testGetPokemonLocationsArea() {
        // Arrange
        PokemonLocationsArea areaWrapper = mock(PokemonLocationsArea.class);
        List<PokemonLocationsArea> areasDto = List.of(areaWrapper);

        when(apiClient.getPokemonLocations("eevee")).thenReturn(areasDto);

        Area soapArea = new Area();
        soapArea.setName("kanto-route-2");
        soapArea.setUrl("url");

        try (MockedStatic<PokemonMapper> mockedMapper = mockStatic(PokemonMapper.class)) {
            mockedMapper.when(() -> PokemonMapper.mapToListLocationAreaResponse(areasDto, "eevee"))
                        .thenReturn(List.of(soapArea));

            // Act
            List<Area> result = pokemonServices.getPokemonLocationsArea("eevee");

            // Assert
            assertEquals(1, result.size());
            assertEquals("kanto-route-2", result.get(0).getName());
        }
    }
    
    
    @Test
    void testGetPokemonItemsException() {
        PokemonItemsDto itemsDto = new PokemonItemsDto();
        when(apiClient.getPokemonItems("bulbasaur")).thenReturn(itemsDto);

        try (MockedStatic<PokemonMapper> mockedMapper = mockStatic(PokemonMapper.class)) {
            mockedMapper.when(() -> PokemonMapper.mapToListItemResponse(itemsDto, "bulbasaur"))
                        .thenThrow(new PokemonException("No items found"));

            Executable executable = () -> pokemonServices.getPokemonItems("bulbasaur");

            PokemonException ex = assertThrows(PokemonException.class, executable);
            assertEquals("No items found", ex.getMessage());
        }
    }

    @Test
    void testGetPokemonLocationsAreaException() {
        List<PokemonLocationsArea> areasDto = List.of();
        when(apiClient.getPokemonLocations("eevee")).thenReturn(areasDto);

        try (MockedStatic<PokemonMapper> mockedMapper = mockStatic(PokemonMapper.class)) {
            mockedMapper.when(() -> PokemonMapper.mapToListLocationAreaResponse(areasDto, "eevee"))
                        .thenThrow(new PokemonException("No areas found"));

            Executable executable = () -> pokemonServices.getPokemonLocationsArea("eevee");

            PokemonException ex = assertThrows(PokemonException.class, executable);
            assertEquals("No areas found", ex.getMessage());
        }
    }

    @Test
    void testApiClientException() {
        when(apiClient.getPokemonAbilities("pikachu"))
                .thenThrow(new PokemonException("API unavailable"));

        Executable executable = () -> pokemonServices.getAbilities("pikachu");

        PokemonException ex = assertThrows(PokemonException.class, executable);
        assertEquals("API unavailable", ex.getMessage());
    }
}
