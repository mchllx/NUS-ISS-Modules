package com.example.backend.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.backend.models.Pokemon;
import com.example.backend.service.PokemonService;

import jakarta.json.JsonArray;
import jakarta.json.JsonObject;
import jakarta.json.JsonValue;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

@Controller
@CrossOrigin(origins={"http://localhost:4200", "https://vttpb4-michelle-lim.up.railway.app", "http://localhost:8080"})
@RequestMapping(path="/api", produces=MediaType.APPLICATION_JSON_VALUE)
public class PokemonController {

    @Autowired
    private PokemonService pokeSvc;

    private Logger logger = Logger.getLogger(PokemonController.class.getName());

    // GET http://localhost:8080/api/pokemon?name=pikachu
    // @GetMapping(path="/pokemon")
    // @ResponseBody
    // public ResponseEntity<Pokemon> getPokemonByName(@RequestParam String name) {
    //     JsonObject results = pokeSvc.getPokemonByName(name);

    //     Pokemon pokemon = new Pokemon();
    //     pokemon = pokemon.fromJSON(results);
        
    //     System.out.println(">>>pokemon:" + pokemon.toString());

    //     return ResponseEntity
    //         .status(HttpStatus.OK)
    //         .contentType(MediaType.APPLICATION_JSON)
    //         .body(pokemon);
    // }

    // GET http://localhost:8080/api/pokemon?limit=10&offset=0
    @GetMapping(path="/pokemon")
    @ResponseBody
    public ResponseEntity<List<Pokemon>> getPokemon(@RequestParam String name, Integer limit, Integer offset) {

        List<Pokemon> pokeList = pokeSvc.getPokemonByNameRedis(name);

        if (pokeList != null) {

            JsonObject results = pokeSvc.getPokemon(limit, offset);
            JsonArray list = results.getJsonArray("results");

            for (JsonValue j: list) {
                JsonObject jsonObj = j.asJsonObject();
                System.out.println(">>jsonObj" + jsonObj.toString());

                String pokeName = jsonObj.getString("name");

                if (!pokeName.contains(name)) {
                    logger.info(">>>Does not match");

                } else {
                    logger.info(">>>Matching record found");
                    JsonObject pokemon = pokeSvc.getPokemonByName(pokeName);

                    Pokemon p = new Pokemon();
                    p = p.fromJSON(pokemon);

                    pokeList.add(p);
                }
            }
            
            pokeSvc.savePokemonRedis(pokeList);
            
            System.out.println(">>>list of pokemon"+ pokeList);
            return ResponseEntity
            .status(HttpStatus.OK)
            .contentType(MediaType.APPLICATION_JSON)
            .body(pokeList);
            
        } else {

            System.out.println(">>>list of pokemon"+ pokeList);

            return ResponseEntity
            .status(HttpStatus.OK)
            .contentType(MediaType.APPLICATION_JSON)
            .body(pokeList);
            
        }
    }

    // GET http://localhost:8080/api/pokemon/25
    @GetMapping(path="/pokemon/{id}")
    @ResponseBody
    public ResponseEntity<Pokemon> getPokemonById(@PathVariable Integer id) {
        JsonObject results = pokeSvc.getPokemonById(id);

        Pokemon pokemon = new Pokemon();
        pokemon = pokemon.fromJSON(results);
        
        System.out.println(">>>pokemon:" + pokemon.toString());

        return ResponseEntity
        .status(HttpStatus.OK)
        .contentType(MediaType.APPLICATION_JSON)
        .body(pokemon);
    }

}
