package sg.edu.nus.iss.d28.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import sg.edu.nus.iss.d28.models.Game;
import sg.edu.nus.iss.d28.services.GameService;

@RestController
@RequestMapping(path="/api/games")
public class GameRestController {

    @Autowired
    GameService gameSvc;

    @GetMapping
    public ResponseEntity<List<Game>> getAllGames() {
        List<Game> games = gameSvc.getAllGames();

        return ResponseEntity
            .status(HttpStatus.OK)
            .contentType(MediaType.APPLICATION_JSON)
            .body(games);  
    }

    @PostMapping
    public ResponseEntity<Game> createGame(@RequestBody Game game) {
        game = gameSvc.createGame(game);

        return ResponseEntity
            .status(HttpStatus.OK)
            .contentType(MediaType.APPLICATION_JSON)
            .body(game);
    }

    @PutMapping
    public ResponseEntity<Long> updateGame(@RequestBody Game game) {
        Long result = gameSvc.updateGame(game);

        return ResponseEntity
            .status(HttpStatus.OK)
            .contentType(MediaType.APPLICATION_JSON)
            .body(result);
    }

    @DeleteMapping
    public ResponseEntity<Long> deleteGame(@RequestBody Game game, int gId) {
        Long result = gameSvc.deleteGame(game, gId);

        return ResponseEntity
            .status(HttpStatus.OK)
            .contentType(MediaType.APPLICATION_JSON)
            .body(result);
    }

}
