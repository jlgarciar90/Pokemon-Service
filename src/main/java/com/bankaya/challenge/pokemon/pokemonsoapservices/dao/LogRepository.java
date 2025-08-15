package com.bankaya.challenge.pokemon.pokemonsoapservices.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bankaya.challenge.pokemon.pokemonsoapservices.model.LogEntity;

public interface LogRepository extends JpaRepository<LogEntity, Long>{

}
