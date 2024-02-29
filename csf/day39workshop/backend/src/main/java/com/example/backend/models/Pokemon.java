package com.example.backend.models;

import java.util.LinkedList;
import java.util.List;

import jakarta.json.Json;
import jakarta.json.JsonArray;
import jakarta.json.JsonObject;

public class Pokemon {

    private Integer id;
    private String name;
    private String url;
    private String image;
    private List<String> abilities;

    public Pokemon() {
    }

    public Pokemon(Integer id, String name, String url, String image, List<String> abilities) {
        this.id = id;
        this.name = name;
        this.url = url;
        this.image = image;
        this.abilities = abilities;
    }

    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public String getUrl() { return url; }
    public void setUrl(String url) { this.url = url; }
    public String getImage() { return image; }
    public void setImage(String image) { this.image = image; }
    public List<String> getAbilities() { return abilities; }
    public void setAbilities(List<String> abilities) { this.abilities = abilities; }

    @Override
    public String toString() {
      return "Pokemon [id="
        .concat("" + this.id)
        .concat(", name=")
        .concat("" + this.name)
        .concat(", url=")
        .concat(this.url)
        .concat(", image=")
        .concat(this.image)
        .concat(", abilities=")
        .concat("" + this.abilities)
        .concat("]");
   }

    public JsonObject toJSON() {
      return Json.createObjectBuilder()
            .add("id", id)
            .add("name", name)
            .add("url", url)
            .add("image", image)
            .add("abilities", abilities.toArray().toString())
            .build();
   }

   public Pokemon fromJSON(JsonObject jsonObj) {
        Pokemon pokemon = new Pokemon();

        pokemon.setId(jsonObj.getInt("id"));
        pokemon.setName(jsonObj.getString("name"));
        pokemon.setUrl(jsonObj.getString("url"));
        pokemon.setImage(jsonObj.getString("image"));

        // System.out.printf(">>>ID:%d\n", jsonObj.getInt("id"));
        // System.out.printf(">>>Name:%s\n", jsonObj.getString("name"));
        // System.out.printf(">>>URL :%s\n", jsonObj.getString("url"));
        // System.out.printf(">>>Image :%s\n", jsonObj.getString("image"));

        JsonArray array = jsonObj.getJsonArray("abilities");

        List<String> list = new LinkedList<>();

        for (int i=0; i<array.size(); i++) {
            // System.out.printf(">>>Processing ability: %s\n", array.get(i).toString());
            String ability = array.get(i).toString().replace('"', ' ').trim();
            // System.out.printf(">>>After processing: %s\n", ability);
            list.add(ability);
        }
        pokemon.setAbilities(list);
        // System.out.printf(">>>Abilities:%s\n", list);

        return pokemon;
   }

}
