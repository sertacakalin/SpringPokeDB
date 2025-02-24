package com.specialproject.pokemonsql.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@RequiredArgsConstructor
@Data
@Entity
@Table(name = "pokemon")
@JsonIgnoreProperties(ignoreUnknown = true)
public class PokemonEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    private String name;
    private String number;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "pokemon_types",
            joinColumns = @JoinColumn(name = "pokemon_id"),
            inverseJoinColumns = @JoinColumn(name = "pokemon_type_id"))

    private Set<PokemonTypeEntity> types = new HashSet<>();

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "pokemon_moves",
            joinColumns = @JoinColumn(name = "pokemon_id"),
            inverseJoinColumns = @JoinColumn(name = "move_id")
    )
    private Set<PokemonMoveEntity> moves = new HashSet<>();
}
