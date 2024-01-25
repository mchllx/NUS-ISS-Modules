package sg.edu.nus.iss.d26workshop.models;

import java.util.List;

import org.joda.time.DateTime;

import jakarta.json.Json;
import jakarta.json.JsonArrayBuilder;
import jakarta.json.JsonObject;

public class Games {

    private List<Game> games;
    private Integer limit;
    private Integer offset;
    private Integer total;
    private DateTime timestamp;

    @Override
    public String toString() {
        return "Game [games= "
                .concat("" + this.games)
                .concat(", limit=")
                .concat("" + this.limit)
                .concat(", total=")
                .concat("" + this.total)
                .concat(", timestamp=")
                .concat("" + this.timestamp)
                .concat("]");
    }

    public Games() { 
    }

    public Games(List<Game> games, Integer limit, Integer offset, Integer total, DateTime timestamp) {
        this.games = games;
        this.limit = limit;
        this.offset = offset;
        this.total = total;
        this.timestamp = timestamp;
    }

    public List<Game> getGames() { return games; }
    public void setGames(List<Game> games) { this.games = games; }
    public Integer getLimit() { return limit; }
    public void setLimit(Integer limit) { this.limit = limit; }
    public Integer getOffset() { return offset; }
    public void setOffset(Integer offset) { this.offset = offset; }
    public Integer getTotal() { return total; }
    public void setTotal(Integer total) { this.total = total; }
    public DateTime getTimestamp() { return timestamp; }
    public void setTimestamp(DateTime timestamp) { this.timestamp = timestamp; }

    public JsonObject toJSON() {
        JsonArrayBuilder gamesBuilder = Json.createArrayBuilder();
        List<JsonObject> games = this.getGames()
            .stream()
            .map(t -> t.toJSON())
            .toList();

        for(JsonObject obj : games)
            gamesBuilder.add(obj);
            
        return Json.createObjectBuilder()
            .add("games", gamesBuilder)
            .add("limit", getLimit() != null? getLimit():0)
            .add("offset", getOffset() != null? getOffset():0)
            .add("total", getTotal() != null? getTotal():0)
            .add("timestamp", getTimestamp().toString())
            .build();
    }
    
}
