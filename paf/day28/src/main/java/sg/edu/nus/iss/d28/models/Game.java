package sg.edu.nus.iss.d28.models;

import java.io.Serializable;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.json.Json;
import jakarta.json.JsonObject;

//serialisable, serialises from document to pojo
@Document(collection="games")
public class Game implements Serializable {

    @Id
    @Field("_id")
    @JsonIgnore
    private String id;

    private Integer gId;
    private String name;
    private Integer year;
    private Integer rating;
    private Integer userRating;

    @Override
    public String toString() {
        return "Game [gId="
            .concat("" + gId)
            .concat(", name=")
            .concat("" + name)
            .concat(", year=")
            .concat("" + year)
            .concat(", rating=")
            .concat("" + rating)
            .concat(", userRating=")
            .concat("" + userRating)
            .concat( "]");
    }

    public Game() {
    }

    public Game(Integer gId, String name, Integer year, Integer rating, Integer userRating) {
        this.gId = gId;
        this.name = name;
        this.year = year;
        this.rating = rating;
        this.userRating = userRating;
    }

    public String getId() { return id; }
    public void setId(String id) { this.id = id;}
    public Integer getgId() { return gId; }
    public void setgId(Integer gId) { this.gId = gId; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public Integer getYear() { return year; }
    public void setYear(Integer year) { this.year = year; }
    public Integer getRating() { return rating; }
    public void setRating(Integer rating) { this.rating = rating; }
    public Integer getUserRating() { return userRating; }
    public void setUserRating(Integer userRating) { this.userRating = userRating; }

    public JsonObject toJSON() {
        return Json.createObjectBuilder()
            .add("gId", this.getgId())
            .add("name", this.getName())
            .add("year", this.getRating())
            .add("userRating", this.getUserRating())
            .build();
    }


    
    
}
