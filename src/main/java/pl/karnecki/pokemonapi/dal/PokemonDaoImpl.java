package pl.karnecki.pokemonapi.dal;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import pl.karnecki.pokemonapi.model.Pokemon;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Repository
public class PokemonDaoImpl implements PokemonDao {


    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public PokemonDaoImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }


    @Override
    public void savePokemon(Long id, String name, String type) {

        Pokemon pokemon = new Pokemon(id, name, type);
        String sql = "INSERT INTO pokemons VALUES ( ?, ?, ?)";
        jdbcTemplate.update(sql, pokemon.getPokemonId(), pokemon.getName(), pokemon.getType());


    }

    @Override
    public List<Pokemon> findAll() {
        String sql = "SELECT * from pokemons";
        List<Pokemon> pokemonList = new ArrayList<>();

        List<Map<String, Object>> maps = jdbcTemplate.queryForList(sql);
        return getPokemonList(pokemonList, maps);
    }

    @Override
    public void updatePokemon(Pokemon newPokemon) {
        String sql = "UPDATE pokemons SET pokemons.name = ? , pokemons.type = ? WHERE pokemons.Id = ? ";
        jdbcTemplate.update(sql, newPokemon.getName(), newPokemon.getType(), newPokemon.getPokemonId());
    }

    @Override
    public void deletePokemon(Long id) {
        String sql = "DELETE FROM pokemons  WHERE Id = ? ";
        jdbcTemplate.update(sql, id);

    }

    @Override
    public Pokemon getPokemonById(Long id) {
        String sql = "SELECT * FROM pokemons WHERE Id = ? ";
        return jdbcTemplate.queryForObject(sql, (resultSet, rowNum) -> new Pokemon(
                resultSet.getLong("id"),
                resultSet.getString("name"),
                resultSet.getString("type")), id);

    }


    @Override
    public List<Pokemon> findByName(String name) {
        String sql = "SELECT * FROM pokemons WHERE name = ?";
        List<Pokemon> pokemonList = new ArrayList<>();
        final List<Map<String, Object>> maps = jdbcTemplate.queryForList(sql, name);
        return getPokemonList(pokemonList, maps);

    }

    @Override
    public List<Pokemon> findByType(String type) {
        String sql = "SELECT * FROM pokemons WHERE type = ?";
        List<Pokemon> pokemonList = new ArrayList<>();
        final List<Map<String, Object>> maps = jdbcTemplate.queryForList(sql, type);
        return getPokemonList(pokemonList, maps);
    }

    private List<Pokemon> getPokemonList(List<Pokemon> pokemonList, List<Map<String, Object>> maps) {
        maps.stream()
                .forEach(element -> pokemonList.add(new Pokemon(Long.parseLong(String.valueOf(element.get("id"))),
                        String.valueOf(element.get("name")),
                        String.valueOf(element.get("type")))));
        return pokemonList;
    }
}
