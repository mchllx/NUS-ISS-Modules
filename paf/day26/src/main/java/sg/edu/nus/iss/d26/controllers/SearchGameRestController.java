package sg.edu.nus.iss.d26.controllers;

import java.util.List;

import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import jakarta.json.Json;
import jakarta.json.JsonArray;
import jakarta.json.JsonArrayBuilder;
import jakarta.json.JsonObject;
import jakarta.json.JsonObjectBuilder;
import sg.edu.nus.iss.d26.models.Game;
import sg.edu.nus.iss.d26.models.Games;
import sg.edu.nus.iss.d26.services.SearchGameService;

@RestController
@RequestMapping("/api/bgg")
public class SearchGameRestController {

    @Autowired
    private SearchGameService searchGameSvc;
   
    //api/bgg/games?limit=10&offset=10
    @GetMapping(path="/games")
    public ResponseEntity<String> getAllGames(@RequestParam Integer limit, Integer offset) {
        
        List<Game> gamesList = searchGameSvc.searchGame(limit, offset);
        //customised json response (a list of json array)

        Games games = new Games();
        games.setGames(gamesList);
        games.setTotal(gamesList.size());
        games.setLimit(limit);
        games.setOffset(offset);
        games.setTimestamp(DateTime.now());

        JsonObjectBuilder objBuilder = Json.createObjectBuilder()
            .add("games", games.toJSON());
        
        JsonObject jsonObject = objBuilder.build();

        //don't use .ok, can't customise
        return ResponseEntity
            .status(HttpStatus.OK)
            .contentType(MediaType.APPLICATION_JSON)
            .body(jsonObject.toString());
    }

    //api/bgg/games/rank
    @GetMapping(path="/games/rank")
    public ResponseEntity<String> getGamesByRanking() {

        List<Game> gamesList = searchGameSvc.getGamesByRank();
        JsonArrayBuilder arrBuilder = Json.createArrayBuilder();

        for(Game g: gamesList)
            arrBuilder.add(g.toJSON());

        JsonArray array = arrBuilder.build();

        return ResponseEntity
        .status(HttpStatus.OK)
        .contentType(MediaType.APPLICATION_JSON)
        .body(array.toString()); 
    }

    //api/bgg/games/174430
    @GetMapping(path="/games/{gId}")
    public ResponseEntity<String> getGameDetailsById(@PathVariable Integer gId) {

        Game gameDetails = searchGameSvc.getGameDetails(gId);
        JsonObjectBuilder objBuilder = Json.createObjectBuilder()
            .add("game", gameDetails.toJSON());

        JsonObject jsonObject = objBuilder.build();

        return ResponseEntity
        .status(HttpStatus.OK)
        .contentType(MediaType.APPLICATION_JSON)
        .body(jsonObject.toString()); 
    }
    

}
