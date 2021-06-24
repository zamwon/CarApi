package pl.karnecki.pokemonapi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import pl.karnecki.pokemonapi.model.Pokemon;
import pl.karnecki.pokemonapi.service.PokemonServiceImpl;
import java.util.List;


@Controller
@RequestMapping("/pokemon")
public class PokemonController {


    private final PokemonServiceImpl pokemonService;

    @Autowired
    public PokemonController(PokemonServiceImpl pokemonService) {
        this.pokemonService = pokemonService;

    }

    @GetMapping("/all")
    public String getAllPokemon(Model model) {
        final List<Pokemon> listOfPokemon = pokemonService.getAll();
        model.addAttribute("listOfPokemon", listOfPokemon);
        model.addAttribute("pokemon1",  new Pokemon());
        return "allPokemonPage";
    }

    @GetMapping("/{id}")
    public ResponseEntity<Pokemon> getPokemonById(@PathVariable Long id) {
        Pokemon first = pokemonService.getPokemonById(id);
        if (first != null) {
            return new ResponseEntity<>(first, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping("/name/{name}")
    public ResponseEntity<List<Pokemon>> getPokemonByName(@PathVariable String name) {

        List<Pokemon> first = pokemonService.getPokemonsByName(name);
        if (!first.isEmpty()) {

            return new ResponseEntity<>(first, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping("/type/{type}")
    public ResponseEntity<List<Pokemon>> getPokemonByType(@PathVariable String type) {

        List<Pokemon> first = pokemonService.getPokemonsByType(type);
        if (!first.isEmpty()) {

            return new ResponseEntity<>(first, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }


    @PostMapping("/add-pokemon")
    public ResponseEntity<HttpStatus> addPokemon(@Validated @RequestBody Pokemon pokemon) {
        boolean isAdded = pokemonService.addPokemon(pokemon);
        if (isAdded) {
            return new ResponseEntity<>(HttpStatus.CREATED);
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @PutMapping()
    public ResponseEntity<HttpStatus> modifyPokemon(@RequestBody Pokemon pokemon) {
        if (pokemon.getPokemonId() != null) {
            pokemonService.modifyPokemon(pokemon);
            return new ResponseEntity<>(HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<HttpStatus> modifyCarField(@PathVariable Long id, @RequestBody Pokemon pokemon) {
        Pokemon oldPokemon = pokemonService.getPokemonById(id);
        if (oldPokemon == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        if (pokemon.getName() != null) {
            oldPokemon.setName(pokemon.getName());
        }
        if (pokemon.getType() != null) {
            oldPokemon.setType(pokemon.getType());

        }
        pokemonService.modifyPokemon(oldPokemon);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> deletePokemon(@PathVariable Long id) {
        Pokemon toDeletePokemon = pokemonService.getPokemonById(id);
        if (toDeletePokemon != null) {
            pokemonService.deletePokemon(id);
            return new ResponseEntity<>(HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
