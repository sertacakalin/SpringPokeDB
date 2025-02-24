package com.specialproject.pokemonsql.model;

import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.util.List;
@RequiredArgsConstructor
@Data
public class PokemonList {
    private List<PokemonDetail> detail;

}
