package com.specialproject.pokemonsql.repository;

import com.specialproject.pokemonsql.entity.PokemonMoveEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface MoveRepository extends JpaRepository<PokemonMoveEntity, UUID> {
    Optional<PokemonMoveEntity> findByName(String name);
}
