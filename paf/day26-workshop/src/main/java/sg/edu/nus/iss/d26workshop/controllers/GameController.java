package sg.edu.nus.iss.d26workshop.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import jakarta.json.JsonObject;
import sg.edu.nus.iss.d26workshop.models.Game;
import sg.edu.nus.iss.d26workshop.models.Games;
import sg.edu.nus.iss.d26workshop.services.GameService;

@Controller
@RequestMapping
public class GameController {

    @Autowired
    private GameService gameSvc;

    // task 1
    // http://localhost:8080/games?limit=10&offset=0
    @GetMapping(path="/games", produces=MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> getSummary(@RequestParam Integer limit, Integer offset) {
        JsonObject results = gameSvc.getSummary(limit, offset);

        return ResponseEntity
            .status(HttpStatus.OK)
            .contentType(MediaType.APPLICATION_JSON)
            .body(results.toString());
    }

    // task 2
    //http://localhost:8080/games/rank?limit=10&offset=0
    @GetMapping(path="/games/rank", produces=MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> getGamesByRank(@RequestParam Integer limit, Integer offset) {
        JsonObject results = gameSvc.getSummaryByRank(limit, offset);

        return ResponseEntity
            .status(HttpStatus.OK)
            .contentType(MediaType.APPLICATION_JSON)
            .body(results.toString());
    }

 
}
