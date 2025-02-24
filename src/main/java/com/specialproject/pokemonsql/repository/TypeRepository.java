package com.specialproject.pokemonsql.repository;

import com.specialproject.pokemonsql.entity.PokemonTypeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface TypeRepository extends JpaRepository<PokemonTypeEntity, UUID> {
    Optional<PokemonTypeEntity> findByName(String name);
}

