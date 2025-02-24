package com.specialproject.pokemonsql.service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.specialproject.pokemonsql.entity.PokemonEntity;
import com.specialproject.pokemonsql.entity.PokemonMoveEntity;
import com.specialproject.pokemonsql.entity.PokemonTypeEntity;
import com.specialproject.pokemonsql.model.PokemonDetail;
import com.specialproject.pokemonsql.repository.MoveRepository;
import com.specialproject.pokemonsql.repository.PokemonDataRepository;
import com.specialproject.pokemonsql.repository.TypeRepository;
import jakarta.annotation.PostConstruct;
import jakarta.transaction.Transactional;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.apache.commons.io.IOUtils;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.InputStream;
import java.util.*;
import java.util.stream.Collectors;

@Data
@Service
@RequiredArgsConstructor
public class PokemonService {

    private final PokemonDataRepository pokemonRepository;
    private final TypeRepository pokemonTypeRepository;
    private final MoveRepository pokemonMoveRepository;
    private final ObjectMapper objectMapper;

    @PostConstruct
    @Transactional
    public void loadPokemonData() throws IOException {
        InputStream jsonStream = getClass().getClassLoader().getResourceAsStream("static/allpokemons.json");
        String json = IOUtils.toString(jsonStream, "UTF-8");
        List<PokemonDetail> pokemonDetails = objectMapper.readValue(json, new TypeReference<>() {
        });

        List<PokemonDetail> pokemonFirst151 = pokemonDetails
                .stream()
                .filter(p -> p.getId() >= 1 && p.getId() <= 151)
                .collect(Collectors.toList());

        List<PokemonEntity> pokemonToSave = new ArrayList<>();

        pokemonFirst151.forEach(pokemonInProgress -> {
            if (!pokemonRepository.existsPokemonByNumber(pokemonInProgress.getNumber())) {
                PokemonEntity pokemonEntity = new PokemonEntity();
                pokemonEntity.setName(pokemonInProgress.getName());
                pokemonEntity.setNumber(pokemonInProgress.getNumber());

                Set<PokemonTypeEntity> collectedTypes = pokemonInProgress
                        .getType()
                        .stream()
                        .map(this::findOrCreateTypeByName)
                        .filter(Optional::isPresent)
                        .map(Optional::get)
                        .collect(Collectors.toSet());

                pokemonEntity.setTypes(collectedTypes);

                Set<PokemonMoveEntity> collectedMoves = pokemonInProgress
                        .getMoves()
                        .stream()
                        .map(this::findOrCreateMoveByName)
                        .filter(Optional::isPresent)
                        .map(Optional::get)
                        .collect(Collectors.toSet());

                pokemonEntity.setMoves(collectedMoves);

                pokemonToSave.add(pokemonEntity);
            }
        });

        if (!pokemonToSave.isEmpty()) {
            pokemonRepository.saveAll(pokemonToSave);
            System.out.println("new pokemons created " + pokemonToSave.size());
        } else {
            System.out.println("failed created");
        }

        System.out.println("Registered Pokemons");
        pokemonRepository.findAll().forEach(pokemon ->
                System.out.println("id: " + pokemon.getId() + ", name: " + pokemon.getName() + ", number: " + pokemon.getNumber()));
    }

    public List<PokemonEntity> findAllPokemonDatabase() {
        return pokemonRepository.findAll();
    }

    public List<PokemonEntity> findPokemonByType(String typeName) {
        return pokemonRepository.findByTypesName(typeName);
    }

    public List<PokemonMoveEntity> findPokemonByMove(String moveName) {
        return pokemonRepository.findByMovesName(moveName);
    }

    private Optional<PokemonTypeEntity> findOrCreateTypeByName(String typeName) {
        System.out.println("Checking type: " + typeName);
        return pokemonTypeRepository.findByName(typeName)
                .or(() -> Optional.of(pokemonTypeRepository.save(new PokemonTypeEntity(typeName))));
    }

    private Optional<PokemonMoveEntity> findOrCreateMoveByName(String moveName) {
        return pokemonMoveRepository.findByName(moveName)
                .or(() -> Optional.of(pokemonMoveRepository.save(new PokemonMoveEntity(moveName))));

    }

    public List<PokemonEntity> searchPokemonByName(String name) {
        return pokemonRepository.queryDbByName(name);
    }
}


