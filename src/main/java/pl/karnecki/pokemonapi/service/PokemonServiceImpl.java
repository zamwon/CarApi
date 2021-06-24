package pl.karnecki.pokemonapi.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.karnecki.pokemonapi.dal.PokemonDao;
import pl.karnecki.pokemonapi.model.Pokemon;
import java.util.List;

import java.util.stream.Collectors;

@Service
public class PokemonServiceImpl implements PokemonService {



    private final PokemonDao pokemonDao;

    @Autowired
    public PokemonServiceImpl(PokemonDao pokemonDao) {
        this.pokemonDao = pokemonDao;
    }


    @Override
    public List<Pokemon> getAll() {
        return pokemonDao.findAll();
    }

    @Override
    public Pokemon getPokemonById(Long id) {
        return pokemonDao.getPokemonById(id);
    }


    @Override
    public List<Pokemon> getPokemonsByName(String name) {
        return pokemonDao.findByName(name)
                .stream()
                .filter(pokemon -> pokemon.getName().equalsIgnoreCase(name))
                .collect(Collectors.toList());
    }
    @Override
    public List<Pokemon> getPokemonsByType(String type) {
        return pokemonDao.findByType(type)
                .stream()
                .filter(pokemon -> pokemon.getType().equalsIgnoreCase(type))
                .collect(Collectors.toList());
    }

    @Override
    public boolean addPokemon(Pokemon pokemon) {
        boolean isPokemonOnList = pokemonDao.findAll()
                .stream()
                .anyMatch(element ->
                        element.getPokemonId().equals(pokemon.getPokemonId()));
        if (isPokemonOnList) return false;
        pokemonDao.savePokemon(pokemon.getPokemonId(), pokemon.getName(), pokemon.getType());
        return true;
    }

    @Override
    public void deletePokemon(Long id) {
        pokemonDao.deletePokemon(id);
    }

    @Override
    public void modifyPokemon(Pokemon pokemon) {
        boolean isPokemonOnList = pokemonDao.findAll()
                .stream()
                .anyMatch(element ->
                        element.getPokemonId().equals(pokemon.getPokemonId()));
        if (isPokemonOnList) {
            pokemonDao.updatePokemon(pokemon);
        }
    }

}
