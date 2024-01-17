package sg.edu.nus.iss.d26.models;

//use this binary json
import org.bson.Document;

import jakarta.json.Json;
import jakarta.json.JsonObject;

public class Game {

    /**
     * {"gid":1, "name":"Die Macher", "year":1986, "ranking":223, "users_rated":4777,
    "url":"https://www.boardgamegeek.com/boardgame/1/die-macher",
    "image":"https://cf.geekdo-images.com/micro/img/JFMB8ORpxWo-VfPrEePfMluBkGs=/fit-in/64x64/pic159509.jpg"}**/
   
    private Integer gid;
    private String name;
    private Integer year;
    private Integer ranking;
    private Integer user_rated;
    private String url;
    private String image;

    public Game() { 
    }

    public Game(Integer gid, String name, Integer year, Integer ranking, Integer user_rated, String url, String image) {
        this.gid = gid;
        this.name = name;
        this.year = year;
        this.ranking = ranking;
        this.user_rated = user_rated;
        this.url = url;
        this.image = image;
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
                .concat("" + this.user_rated)
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
    public Integer getUser_rated() { return user_rated; }
    public void setUser_rated(Integer user_rated) { this.user_rated = user_rated; }
    public String getUrl() { return url; }
    public void setUrl(String url) { this.url = url; }
    public String getImage() { return image; }
    public void setImage(String image) { this.image = image; }

    //use getter and setter
    public JsonObject toJSON() {
        return Json.createObjectBuilder()
            .add("gid", getGid()) 
            .add("name", getName()) 
            .add("year", getYear()) 
            .add("ranking", getRanking()) 
            .add("user_rated", getUser_rated() != null? getUser_rated():0) 
            .add("url", getUrl()) 
            .add("image", getImage()) 
            .build(); 
    }

    //return game object
    public static Game fromJSON(Document d) {
       Game game = new Game();
       game.setGid((d.getInteger("gid"))); 
       game.setName((d.getString("name"))); 
       game.setYear((d.getInteger("year"))); 
       game.setRanking((d.getInteger("ranking"))); 
       game.setUser_rated((d.getInteger("user_rated"))); 
       game.setUrl((d.getString("url"))); 
       game.setImage((d.getString("image"))); 

       return game;
    }
}
