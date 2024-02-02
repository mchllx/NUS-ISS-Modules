package sg.edu.nus.iss.d26workshop.controllers;

import java.util.Date;
import java.util.List;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import jakarta.json.Json;
import jakarta.json.JsonObject;
import jakarta.json.JsonObjectBuilder;
import sg.edu.nus.iss.d26workshop.exception.GameNotFoundException;
import sg.edu.nus.iss.d26workshop.models.Comment;
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

    // task 3
    //http://localhost:8080/games/174430, http://localhost:8080/games/272410, http://localhost:8080/games/0
    @GetMapping(path="/games/{game_id}", produces=MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> getGameById(@PathVariable(name="game_id") Integer game_id) {

        try {
            if (game_id == null || game_id <= 0) {
                throw new GameNotFoundException("Invalid game id");
            } 
        } catch (GameNotFoundException e1) {
            e1.printStackTrace();
            return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .contentType(MediaType.APPLICATION_JSON)
                .body("Invalid game id");
        }
            
        //handle non-existence game ids
        try {
            JsonObject results = gameSvc.getDetailsById(game_id);
            return ResponseEntity
            .status(HttpStatus.OK)
            .contentType(MediaType.APPLICATION_JSON)
            .body(results.toString());

        } catch (NullPointerException e2) {
            e2.printStackTrace();
            return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .contentType(MediaType.APPLICATION_JSON)
                .body("Game does not exist");
        }
    }

    //task1 d27workshop
    @GetMapping(path="/review")
    public ModelAndView showForm() {
        return new ModelAndView("form", "comment", new Comment());
    }
   
    //http://localhost:8080/review
    //game_id=174430&name=test&rating=1&comment=bad
    @PostMapping(path="/review",
    consumes=MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    public ResponseEntity<String> postReview(@RequestBody MultiValueMap<String, Object> mvm) {
        
        Integer gId = Integer.parseInt(mvm.getFirst("gid").toString());
        String name = mvm.getFirst("name").toString();
        int rating = Integer.parseInt(mvm.getFirst("rating").toString());
        String text = mvm.getFirst("text").toString();

        Game game;

        try {
            game = gameSvc.getGameById(gId);
        } catch (GameNotFoundException e2) {
            return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                .body("Invalid game id");
        }

        String gName = game.getName();
        
        // System.out.println(mvm.getFirst("name"));
        // System.out.println(mvm.getFirst("rating"));
        // System.out.println(mvm.getFirst("text"));

        JsonObjectBuilder objBuilder = Json.createObjectBuilder()
            .add("user", name)
            .add("rating", rating)
            .add("comment", text)
            .add("id", gId)
            .add("posted", new Date().toString())
            .add("name", gName);

        JsonObject results = objBuilder.build();

        gameSvc.updateCommentById(gId, results);

        return ResponseEntity
            .status(HttpStatus.OK)
            .contentType(MediaType.APPLICATION_JSON)
            .body(results.toString());
 
    }

    @PutMapping(path="/review/{review_id}")
    public ResponseEntity<String> postReviewById() {
        return null;
    }
}

