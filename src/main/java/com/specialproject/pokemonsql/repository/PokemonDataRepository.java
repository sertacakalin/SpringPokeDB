package com.specialproject.pokemonsql.repository;

import com.specialproject.pokemonsql.entity.PokemonEntity;
import com.specialproject.pokemonsql.entity.PokemonMoveEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface PokemonDataRepository extends JpaRepository<PokemonEntity, UUID> {
    boolean existsPokemonByNumber(String number);

    List<PokemonEntity> findByTypesName(String typeName);

    List<PokemonMoveEntity> findByMovesName(String moveName);

    List<PokemonEntity> queryDbByName(String name);
}

