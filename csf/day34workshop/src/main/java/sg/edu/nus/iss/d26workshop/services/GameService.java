package sg.edu.nus.iss.d26workshop.services;

import org.bson.Document;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.json.Json;
import jakarta.json.JsonArrayBuilder;
import jakarta.json.JsonObject;
import jakarta.json.JsonObjectBuilder;
import sg.edu.nus.iss.d26workshop.exception.GameNotFoundException;
import sg.edu.nus.iss.d26workshop.models.Comment;
import sg.edu.nus.iss.d26workshop.models.Game;
import sg.edu.nus.iss.d26workshop.models.Games;
import sg.edu.nus.iss.d26workshop.repositories.GameRepository;

import java.util.List;

@Service
public class GameService {

    @Autowired
    private GameRepository gameRepo;

    // @Autowired
    // private CommentRepository commentRepo;

    public JsonObject getSummary(Integer limit, Integer offset) {
        //{ games:[{game_id:, name:}], offset:, limit:, total:, timestamp:}
        Games games = new Games();
        games.setGames(gameRepo.get(limit, offset));

        JsonArrayBuilder arrBuilder = Json.createArrayBuilder();
        for (int i = 0; i < games.getGames().size(); i++) {
        JsonObjectBuilder objBuilder = Json.createObjectBuilder()
            .add("game_id", games.getGames().get(i).getGid())
            .add("name", games.getGames().get(i).getName());

        arrBuilder.add(objBuilder.build());
        // System.out.println(jsonObj.toString());
        }

        games.setLimit(limit);
        games.setOffset(offset);
        games.setTotal(count());
        games.setTimestamp(DateTime.now());

        JsonObjectBuilder gameBuilder = Json.createObjectBuilder()
            .add("games", arrBuilder)
            .add("offset", games.getOffset())
            .add("limit", games.getLimit()) 
            .add("total", games.getTotal())
            .add("timestamp", games.getTimestamp().toString());
        
        return gameBuilder.build();
    }

    public JsonObject getSummaryByRank(Integer limit, Integer offset) {
        //sorted by rank ASC { games:[{game_id:, name:}], offset:, limit:, total:, timestamp:}
        Games games = new Games();
        games.setGames(gameRepo.getByRanking(limit, offset));

        JsonArrayBuilder arrBuilder = Json.createArrayBuilder();
        for (int i = 0; i < games.getGames().size(); i++) {
        JsonObjectBuilder objBuilder = Json.createObjectBuilder()
            .add("game_id", games.getGames().get(i).getGid())
            .add("name", games.getGames().get(i).getName())
            .add("ranking", games.getGames().get(i).getRanking());

        arrBuilder.add(objBuilder.build());
        // System.out.println(jsonObj.toString());
        }

        games.setLimit(limit);
        games.setOffset(offset);
        games.setTotal(count());
        games.setTimestamp(DateTime.now());

        JsonObjectBuilder gameBuilder = Json.createObjectBuilder()
            .add("games", arrBuilder)
            .add("offset", games.getOffset())
            .add("limit", games.getLimit()) 
            .add("total", games.getTotal())
            .add("timestamp", games.getTimestamp().toString());
        
        return gameBuilder.build();
    }

    public JsonObject getDetailsById(Integer gId) {
        //{game_id, name, year, ranking, average, users_rated, url, thumbnail, timestamp}
        Game game = gameRepo.getGameById(gId);

        // System.out.println("Gid" + game.getGid());

        JsonObjectBuilder gameBuilder = Json.createObjectBuilder()
            .add("game_id", game.getGid())
            .add("name", game.getName())
            .add("year", game.getYear())
            .add("ranking", game.getRanking())
            .add("average", "not available")
            .add("users_rated", game.getUsersRated() != null? game.getUsersRated():0)
            .add("url", game.getUrl() != null? game.getUrl():"")
            .add("thumbnail", game.getImage() != null? game.getImage():"")
            .add("timestamp", DateTime.now().toString());

        return gameBuilder.build();
    }

    public Integer count() {
        return gameRepo.get(0, 0).size();
    }

    public List<Game> get(Integer limit, Integer offset) {
        return gameRepo.get(limit, offset);
    }

    public Game getGameById(Integer gid) throws GameNotFoundException {
        return gameRepo.getGameById(gid);
    }

    public List<Game> getGamesByRank(Integer limit, Integer offset) {
        return gameRepo.getByRanking(limit, offset);
    }

    public void updateCommentById(Integer gid, JsonObject results) {
        gameRepo.updateCommentById(gid, results);
    }

    public List<Comment> getCommentById(Integer gid) {
        return gameRepo.getCommentById(gid);
    }

    public boolean hasCommentId(String cid) {
        if (gameRepo.getCommentByCId(cid) == null) {
            return false;
        } else {
            return true;
        } 
    }

    public JsonObject getCommentByCId(String cid) {
        return gameRepo.getCommentByCId(cid);
    }
    
}
