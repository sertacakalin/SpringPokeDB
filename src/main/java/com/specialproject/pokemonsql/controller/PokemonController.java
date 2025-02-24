package com.specialproject.pokemonsql.controller;

import com.specialproject.pokemonsql.entity.PokemonEntity;
import com.specialproject.pokemonsql.repository.PokemonDataRepository;
import com.specialproject.pokemonsql.service.PokemonService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequiredArgsConstructor
@RequestMapping("/api")
@RestController
public class PokemonController {
    private final PokemonDataRepository pokemonRepository;
    private final PokemonService pokemonService;


    @GetMapping("/all")
    public List<PokemonEntity> getAllPokemon() {
        return pokemonRepository.findAll();
    }

    @GetMapping("/name/{name}")
    public List<PokemonEntity> findPokemonByType(@PathVariable String name) {
        return pokemonRepository.queryDbByName(name);
    }

}
