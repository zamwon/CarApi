package pl.karnecki.pokemonapi.service;

import pl.karnecki.pokemonapi.model.Pokemon;

import java.util.List;


public interface PokemonService {


    List<Pokemon> getAll();

    Pokemon getPokemonById(Long id);

    List<Pokemon> getPokemonsByName(String name);

    List<Pokemon> getPokemonsByType(String type);

    boolean addPokemon(Pokemon pokemon);

    void deletePokemon(Long id);

    void modifyPokemon(Pokemon pokemon);


}
