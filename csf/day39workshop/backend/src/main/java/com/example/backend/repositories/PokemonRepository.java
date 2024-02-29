package com.example.backend.repositories;

import java.time.Duration;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.redis.core.ListOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Repository;

import com.example.backend.Utils;
import com.example.backend.models.Pokemon;

@Repository
public class PokemonRepository {

    @Autowired @Qualifier(Utils.BEAN_REDIS)
    private RedisTemplate<String, String> template;

    public static final String key = "pokemon";

    public void savePokemon(List<Pokemon> pokeList){
        pokeList.stream()
            .forEach(p -> {
            String value = "%s,%s,%s,%s,%s".formatted(
                p.getId().toString(), p.getName(), p.getUrl().trim(),
                p.getImage().trim(), p.getAbilities().toString());

            template.opsForList().leftPush(key, value);
            System.out.println(">>>Saved to redis" + value);
        });

        // Set the expiration time for the key (list) to 1 hour
        template.expire(key, 1, TimeUnit.HOURS);
    }

    public List<Pokemon> getPokemon() {
        List<String> list = template.opsForList().range(key, 0, -1);

        List<Pokemon> results = new LinkedList<>();

        for (String s: list) {
            String[] terms = s.split(",");

            Pokemon p = new Pokemon();

            Integer id = Integer.parseInt(terms[0]);
            String name = terms[1];
            String url = terms[2];
            String image = terms[3];
            String rawAbilities = terms[4];

            List<String> abilities = new LinkedList<>();

            String[] ability = rawAbilities.split(",");

            for (int i=0; i<ability.length; i++) {
                abilities.add(ability[i]);
            }
            
            p.setId(id);
            p.setName(name);
            p.setUrl(url);
            p.setImage(image);
            p.setAbilities(abilities);

            results.add(p);
        }

        System.out.println(">>> redis pokemon:"+ results); 
        return results;
    }
}