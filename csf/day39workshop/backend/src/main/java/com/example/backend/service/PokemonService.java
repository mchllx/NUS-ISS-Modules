package com.example.backend.service;

import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import com.example.backend.models.Pokemon;
import com.example.backend.repositories.PokemonRepository;

import jakarta.json.Json;
import jakarta.json.JsonArray;
import jakarta.json.JsonArrayBuilder;
import jakarta.json.JsonObject;
import jakarta.json.JsonObjectBuilder;
import jakarta.json.JsonReader;

@Service
public class PokemonService {

    @Autowired
    PokemonRepository pokeRepo;

    final String BASE_URL = "https://pokeapi.co";
    
    private Logger logger = Logger.getLogger(PokemonService.class.getName());
    RestTemplate template = new RestTemplate();

    // TODO: Task 2
    // GET https://pokeapi.co/api/v2/pokemon/pikachu
    /** {
     *      abilities:[{ability: {name:, url:}, ...},{ability: {name:,url:}...}]
     *      base_experience: number,
     *      cries: {latest:, legacy:},
     *      forms: [{name:, url}],
     *      ...
     *      id: number,
     *      ...
     *      moves: [{move:{name:, url:},...},{move:{name:,url:}...},...],
     *      name: "pikachu", string,
     *      ...
     *      species: {name: string, url: string},
     *      sprites: {..., front_default: string} 
     * 
     **/
   
    public List<Pokemon> getPokemonByNameRedis(String name) {

        List<Pokemon> redis = pokeRepo.getPokemon();
        List<Pokemon> results = new LinkedList<>();
        
        if (results.size() <= 0) {
            System.out.println(">>>Pokemon not found in redis");
            return results;
        }

        if (results.get(0).getName().contains(name)) {

            for (Pokemon p: redis) {
                results.add(p);
            }
        }
        
        return results;
    }

    public void savePokemonRedis(List<Pokemon> pokeList) {
        pokeRepo.savePokemon(pokeList);
    } 
    
    public JsonObject getPokemonByName(String name) {
        System.out.printf("Name: %s\n", name);

        JsonObject results = null;

        // .query() "?", .path() ""
        String url = UriComponentsBuilder
            .fromHttpUrl(BASE_URL)
            .path("/api/v2/pokemon/")
            .pathSegment(name)
            .build()
            .toUriString();
        // System.out.println("Url" + url);

        RequestEntity<Void> req = RequestEntity
            .get(url)
            .accept(MediaType.APPLICATION_JSON)
            .build();
       
        ResponseEntity<String> resp = null;

        try {
            resp = template.exchange(req, String.class);

        } catch (RestClientException e1) {
            logger.info("Request Failed: Server Error");
            e1.printStackTrace();
            results = Json.createObjectBuilder().add("pokemon", "Not found").build();
        }

        if (resp != null && resp.getStatusCode().is2xxSuccessful()) {
            JsonReader jr = Json.createReader(new StringReader(resp.getBody()));
            // System.out.println("JR" + jr.toString());

            JsonObjectBuilder jsonObjBuilder = Json.createObjectBuilder();
            JsonObject jsonObj = jr.readObject();

            // id, name, url, image, abilities
            Integer pokeId = jsonObj.getInt("id");
            String pokeName = jsonObj.getString("name");

            StringBuilder sb = new StringBuilder()
                .append(url)
                .append("/")
                .append(pokeId.toString());
            String pokeUrl = sb.toString();

            System.out.println("pokeUrl" +pokeUrl);
            String pokeSprite = "https://www.serebii.net/itemdex/sprites/sv/pokeball.png";

            try {
                pokeSprite = jsonObj.getJsonObject("sprites").getString("front_default");
            } catch (Exception e2) {
                logger.info("Error processing record");
                e2.printStackTrace(); 
            }
 
            JsonArray pokeAbilities = jsonObj.getJsonArray("abilities");

            JsonArrayBuilder array = Json.createArrayBuilder();
        
            for (int i=0; i<pokeAbilities.size(); i++) {
                // System.out.println(pokeAbilities.getJsonObject(i).getJsonObject("ability"));
                String ability = pokeAbilities
                    .getJsonObject(i)
                    .getJsonObject("ability")
                    .getString("name");
                // System.out.printf(">>>ability:%s\n", ability);

                array.add(ability);
            }

            jsonObjBuilder.add("id", pokeId);
            jsonObjBuilder.add("name", pokeName);
            jsonObjBuilder.add("url", pokeUrl);
            jsonObjBuilder.add("image", pokeSprite);
            jsonObjBuilder.add("abilities", array);
            
            results = jsonObjBuilder.build();

            System.out.printf("%s\n", results.toString());

            return results;
        }

        // System.out.printf("%s\n", results.toString());
        return results;
    }

    // TODO: Task 2
    // GET https://pokeapi.co/api/v2/pokemon/25
    /** {
     *      abilities:[{ability: {name:, url:}, ...},{ability: {name:,url:}...}]
     *      base_experience: number,
     *      cries: {latest:, legacy:},
     *      forms: [{name:, url}],
     *      ...
     *      id: number,
     *      ...
     *      moves: [{move:{name:, url:},...},{move:{name:,url:}...},...],
     *      name: "pikachu", string,
     *      ...
     *      species: {name: string, url: string},
     *      sprites: {..., front_default: string} 
     * 
     **/
    
    public JsonObject getPokemonById(Integer id) {
        System.out.printf("Id: %d\n", id);

        JsonObject results = null;

        // .query() "?", .path() ""
        String url = UriComponentsBuilder
            .fromHttpUrl(BASE_URL)
            .path("/api/v2/pokemon/")
            .pathSegment(id.toString())
            .build()
            .toUriString();
        // System.out.println("Url" + url);

        RequestEntity<Void> req = RequestEntity
            .get(url)
            .accept(MediaType.APPLICATION_JSON)
            .build();
       
        ResponseEntity<String> resp = null;

        try {
            resp = template.exchange(req, String.class);

        } catch (RestClientException e1) {
            logger.info("Request Failed: Server Error");
            e1.printStackTrace();
            results = Json.createObjectBuilder().add("pokemon", "Not found").build();
        }

        
        if (resp != null && resp.getStatusCode().is2xxSuccessful()) {
            JsonReader jr = Json.createReader(new StringReader(resp.getBody()));
            // System.out.println("JR" + jr.toString());

            JsonObjectBuilder jsonObjBuilder = Json.createObjectBuilder();
            JsonObject jsonObj = jr.readObject();

            // id, name, url, image, abilities
            Integer pokeId = jsonObj.getInt("id");
            String pokeName = jsonObj.getString("name");

            StringBuilder sb = new StringBuilder()
                .append(url)
                .append("/")
                .append(pokeId.toString());
            String pokeUrl = sb.toString();

            String pokeSprite = "https://www.serebii.net/itemdex/sprites/sv/pokeball.png";

            try {
                pokeSprite = jsonObj.getJsonObject("sprites").getString("front_default");
            } catch (Exception e2) {
                logger.info("Error processing record");
                e2.printStackTrace(); 
            }

            JsonArray pokeAbilities = jsonObj.getJsonArray("abilities");

            JsonArrayBuilder array = Json.createArrayBuilder();
        
            for (int i=0; i<pokeAbilities.size(); i++) {
                // System.out.println(pokeAbilities.getJsonObject(i).getJsonObject("ability"));
                String ability = pokeAbilities
                    .getJsonObject(i)
                    .getJsonObject("ability")
                    .getString("name");
                // System.out.printf(">>>ability:%s\n", ability);

                array.add(ability);
            }

            jsonObjBuilder.add("id", pokeId);
            jsonObjBuilder.add("name", pokeName);
            jsonObjBuilder.add("url", pokeUrl);
            jsonObjBuilder.add("image", pokeSprite);
            jsonObjBuilder.add("abilities", array);
            
            results = jsonObjBuilder.build();

            System.out.printf("%s\n", results.toString());
            return results;
        }

        // System.out.printf("%s\n", results.toString());
        return results;
    }

    // GET https://pokeapi.co/api/v2/pokemon?limit=10&offset=0
    /** {
     *      count: number,
     *      next: string,
     *      previous: string,
     *      results: [{name:, url}], 
     **/
    public JsonObject getPokemon(Integer limit, Integer offset) {
        System.out.printf("Limit: %d, Offset:%d\n", limit, offset);

        JsonObject results = null;

        // .query() "?", .path() ""
        String url = UriComponentsBuilder
            .fromHttpUrl(BASE_URL)
            .path("/api/v2/pokemon/")
            .queryParam("limit", limit.toString())
            .queryParam("offset", offset.toString())
            .build()
            .toUriString();
        // System.out.println("Url" + url);

        RequestEntity<Void> req = RequestEntity
            .get(url)
            .accept(MediaType.APPLICATION_JSON)
            .build();
       
        ResponseEntity<String> resp = null;

        try {
            resp = template.exchange(req, String.class);

        } catch (RestClientException e1) {
            logger.info("Request Failed: Server Error");
            e1.printStackTrace();
            results = Json.createObjectBuilder().add("pokemon", "Not found").build();
        }

        if (resp != null && resp.getStatusCode().is2xxSuccessful()) {
            JsonReader jr = Json.createReader(new StringReader(resp.getBody()));
            // System.out.println("JR" + jr.toString());

            // count, next, previous, results
            JsonObject jsonObj = jr.readObject();

            JsonObjectBuilder jsonObjBuilder = Json.createObjectBuilder()
                .add("count", jsonObj.getInt("count"))
                .add("results", jsonObj.getJsonArray("results"));

            results = jsonObjBuilder.build();

            // System.out.printf("%s\n", results.toString());
            return results;
        }

        // System.out.printf("%s\n", results.toString());
        return results;
    }
 
}

// List<JsonObject> abilities = new ArrayList<>();

// abilities = pokeAbilities.stream()
//     .map(j -> j.asJsonObject())
//     .toList();