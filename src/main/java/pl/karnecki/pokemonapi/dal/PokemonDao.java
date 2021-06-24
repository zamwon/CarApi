package pl.karnecki.pokemonapi.dal;

import pl.karnecki.pokemonapi.model.Pokemon;

import java.util.List;


public interface PokemonDao {

    void savePokemon(Long id, String name, String type);
    List<Pokemon> findAll();
    void updatePokemon(Pokemon pokemon);
    void deletePokemon(Long id);
    Pokemon getPokemonById(Long id);
    List<Pokemon> findByName(String name);
    List<Pokemon> findByType(String type);
}
