package sg.edu.nus.iss.d26workshop.services;

import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.json.Json;
import jakarta.json.JsonArrayBuilder;
import jakarta.json.JsonObject;
import jakarta.json.JsonObjectBuilder;
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

    public List<Game> get(Integer limit, Integer offset) {
        return gameRepo.get(limit, offset);
    }

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

    public Integer count() {
        return gameRepo.get(0, 0).size();
    }

    public List<Game> getGamesByRank(Integer limit, Integer offset) {
        return gameRepo.getByRanking(limit, offset);
    }

    public Game getGameById(Integer gid) {
        return gameRepo.getGameById(gid);
    }
    
}
