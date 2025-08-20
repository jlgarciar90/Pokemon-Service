package com.bankaya.challenge.pokemon.pokemonsoapservices.mapper;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.List;

import org.junit.jupiter.api.Test;

import com.bankaya.challenge.pokemon.pokemonsoapservices.dto.ItemObject.Item;
import com.bankaya.challenge.pokemon.pokemonsoapservices.dto.PokemonItemsDto;
import com.bankaya.challenge.pokemon.pokemonsoapservices.dto.PokemonLocationsArea;
import com.bankaya.challenge.pokemon.pokemonsoapservices.dto.PokemonLocationsArea.Area;
import com.bankaya.challenge.pokemon.pokemonsoapservices.exception.PokemonException;



class PokemonMapperTest {

    @Test
    void testMapToListItemResponse_Success() {
        // Arrange
        Item itemDto = mock(Item.class);
        when(itemDto.getName()).thenReturn("potion");
        when(itemDto.getUrl()).thenReturn("https://pokeapi.co/api/v2/item/1/");
        
        PokemonItemsDto dto = mock(PokemonItemsDto.class);
        // simulamos wrapper
        var wrapper = mock(com.bankaya.challenge.pokemon.pokemonsoapservices.dto.ItemObject.class);
        when(wrapper.getItem()).thenReturn(itemDto);
        when(dto.getItems()).thenReturn(List.of(wrapper));

        List<com.bankaya
        .challenge
        .pokemon.pokemonsoapservices.soap.Item> result = PokemonMapper.mapToListItemResponse(dto, "pikachu");

        // Assert
        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals("potion", result.get(0).getName());
        assertEquals("https://pokeapi.co/api/v2/item/1/", result.get(0).getUrl());
    }

    @Test
    void testMapToListItemResponseExceptionWhenListEmptyOrnull() {
        // Arrange
        PokemonItemsDto dto = mock(PokemonItemsDto.class);
        when(dto.getItems()).thenReturn(List.of()); // lista vacía

        // Act + Assert
        PokemonException ex = assertThrows(PokemonException.class,
                () -> PokemonMapper.mapToListItemResponse(dto, "pikachu"));

        assertEquals("No se encontraron items para el pokemon: pikachu", ex.getMessage());
        
        PokemonItemsDto dtoNull = mock(PokemonItemsDto.class);
        when(dtoNull.getItems()).thenReturn(null); // lista null
        
        PokemonException exNull = assertThrows(PokemonException.class,
                () -> PokemonMapper.mapToListItemResponse(dtoNull, "pikachu"));
        assertEquals("No se encontraron items para el pokemon: pikachu", exNull.getMessage());
    }

    @Test
    void testMapToListLocationAreaResponse_Success() {
        // Arrange
        Area areaDto = mock(Area.class);
        when(areaDto.getName()).thenReturn("kanto-route-2");
        when(areaDto.getUrl()).thenReturn("https://pokeapi.co/api/v2/location-area/1/");

        var wrapper = mock(PokemonLocationsArea.class);
        when(wrapper.getArea()).thenReturn(areaDto);

        List<PokemonLocationsArea> dtoList = List.of(wrapper);

        // Act
        List<com.bankaya
        .challenge
        .pokemon
        .pokemonsoapservices.soap.Area> result = PokemonMapper.mapToListLocationAreaResponse(dtoList, "charizard");

        // Assert
        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals("kanto-route-2", result.get(0).getName());
        assertEquals("https://pokeapi.co/api/v2/location-area/1/", result.get(0).getUrl());
    }

    @Test
    void testMapToListLocationAreaResponse_ThrowsExceptionWhenEmpty() {
        // Arrange
        List<PokemonLocationsArea> emptyList = List.of();

        // Act + Assert
        PokemonException ex = assertThrows(PokemonException.class,
                () -> PokemonMapper.mapToListLocationAreaResponse(emptyList, "bulbasaur"));

        assertEquals("No se encontraron areas para el pokemon: bulbasaur", ex.getMessage());
        
        List<PokemonLocationsArea> nullList = null;
        PokemonException exNull = assertThrows(PokemonException.class,
                () -> PokemonMapper.mapToListLocationAreaResponse(nullList, "bulbasaur"));
        assertEquals("No se encontraron areas para el pokemon: bulbasaur", exNull.getMessage());
    }
}
