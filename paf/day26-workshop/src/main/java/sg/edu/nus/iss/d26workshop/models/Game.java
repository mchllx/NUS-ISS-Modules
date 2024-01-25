package sg.edu.nus.iss.d26workshop.models;

import org.bson.Document;

import jakarta.json.Json;
import jakarta.json.JsonObject;

public class Game {

// [{"gid":1, "name":"Die Macher", "year":1986, "ranking":223,
// "users_rated":4777, "url":"https://www.boardgamegeek.com/boardgame/1/die-macher",
// "image":"https://cf.geekdo-images.com/micro/img/JFMB8ORpxWo-VfPrEePfMluBkGs=/fit-in/64x64/pic159509.jpg"},

    private Integer gid;
    private String name;
    private Integer year;
    private Integer ranking;
    private Integer usersRated;
    private String url;
    private String image;

    public Game(Integer gid, String name, Integer year, Integer ranking, Integer usersRated, String url, String image) {
        this.gid = gid;
        this.name = name;
        this.year = year;
        this.ranking = ranking;
        this.usersRated = usersRated;
        this.url = url;
        this.image = image;
    }

    public Game() {
    }

    @Override
    public String toString() {
        return "Game [gid= "
                .concat("" + this.gid)
                .concat(", name=")
                .concat(this.name)
                .concat(", year=")
                .concat("" + this.year)
                .concat(", ranking=")
                .concat("" + this.ranking)
                .concat(", usersRated=")
                .concat("" + this.usersRated)
                .concat(", url=")
                .concat(this.url)
                .concat(", image=")
                .concat(this.image)
                .concat("]");
    }

    public Integer getGid() { return gid; }
    public void setGid(Integer gid) { this.gid = gid; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public Integer getYear() { return year; }
    public void setYear(Integer year) { this.year = year; }
    public Integer getRanking() { return ranking; }
    public void setRanking(Integer ranking) { this.ranking = ranking; }
    public Integer getUsersRated() { return usersRated; }
    public void setUsersRated(Integer usersRated) { this.usersRated = usersRated; }
    public String getUrl() { return url; }
    public void setUrl(String url) { this.url = url; }
    public String getImage() { return image; }
    public void setImage(String image) { this.image = image; }

    public JsonObject toJSON() {
        return Json.createObjectBuilder()
            .add("gid", getGid()) 
            .add("name", getName()) 
            .add("year", getYear()) 
            .add("ranking", getRanking()) 
            .add("users_rated", getUsersRated() != null? getUsersRated():0) 
            .add("url", getUrl() != null? getUrl():"") 
            .add("image", getImage() != null? getImage():"")
            .build(); 
    }

    public static Game fromJSON(Document d) {
        Game game = new Game();
        game.setGid((d.getInteger("gid"))); 
        game.setName((d.getString("name"))); 
        game.setYear((d.getInteger("year"))); 
        game.setRanking((d.getInteger("ranking"))); 
        game.setUsersRated((d.getInteger("users_rated"))); 
        game.setUrl((d.getString("url"))); 
        game.setImage((d.getString("image"))); 
 
        return game;
    }

    public static JsonObject BSONtoJSON(Document d) {
        return Json.createObjectBuilder()
            .add("gid", d.getInteger("gid", 0))
            .add("name", d.getString("name"))
            .add("year", d.getInteger("year", 0))
            .add("ranking", d.getInteger("ranking", 0))
            .add("users_rated", d.getInteger("users_rated", 0))
            .add("url", d.getString("url") != null? d.getString("url"):"")
            .add("image", d.getString("url") != null? d.getString("url"):"")
            .build();
    }
    
}
