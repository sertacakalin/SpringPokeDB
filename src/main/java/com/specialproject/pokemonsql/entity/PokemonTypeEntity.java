    package com.specialproject.pokemonsql.entity;

    import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
    import jakarta.persistence.*;
    import lombok.Data;
    import lombok.RequiredArgsConstructor;

    import java.util.UUID;
    @RequiredArgsConstructor
    @Data
    @Entity
    @Table(name = "pokemon_type")
    @JsonIgnoreProperties(ignoreUnknown = true)
    public class PokemonTypeEntity {

        @Id
        @GeneratedValue(strategy = GenerationType.AUTO)
        private UUID id;
        private String name;

        public PokemonTypeEntity(String type) {
            this.name=type;

        }
    }
