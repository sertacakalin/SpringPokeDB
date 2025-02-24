package com.specialproject.pokemonsql.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;
@NoArgsConstructor
@Data
@Entity
@Table(name = "pokemon_move")
@JsonIgnoreProperties(ignoreUnknown = true)
public class PokemonMoveEntity {
    public PokemonMoveEntity(String name) {
        this.name = name;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    private String name;

    public PokemonMoveEntity(UUID id, String name) {
        this.id = id;
        this.name = name;
    }
}
