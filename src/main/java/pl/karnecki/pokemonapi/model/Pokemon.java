package pl.karnecki.pokemonapi.model;

import javax.validation.constraints.NotNull;
import java.util.Objects;


public class Pokemon {

    @NotNull(message = "id cannot be null")
    Long pokemonId;
    String name;
    String type;


    public Pokemon(@NotNull(message = "id cannot be null") Long pokemonId, String name, String type) {
        this.pokemonId = pokemonId;
        this.name = name;
        this.type = type;
    }

    public Pokemon() {
    }

    public Long getPokemonId() {
        return pokemonId;
    }

    public void setPokemonId(Long pokemonId) {
        this.pokemonId = pokemonId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Pokemon pokemon = (Pokemon) o;
        return Objects.equals(pokemonId, pokemon.pokemonId) && Objects.equals(name, pokemon.name) && Objects.equals(type, pokemon.type);
    }

    @Override
    public int hashCode() {
        return Objects.hash(pokemonId, name, type);
    }

    @Override
    public String toString() {
        return "Pokemon{" +
                "pokemonId=" + pokemonId +
                ", name='" + name + '\'' +
                ", type='" + type + '\'' +
                '}';
    }
}
