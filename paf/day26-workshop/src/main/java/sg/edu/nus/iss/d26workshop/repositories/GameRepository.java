package sg.edu.nus.iss.d26workshop.repositories;

import java.util.List;
import java.util.logging.Logger;

import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.aggregation.DocumentOperators.Rank;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import com.mongodb.internal.connection.QueryResult;

import jakarta.json.JsonObject;
import sg.edu.nus.iss.d26workshop.exception.GameNotFoundException;
import sg.edu.nus.iss.d26workshop.models.Game;

@Repository
public class GameRepository {

    @Autowired
    MongoTemplate mongoTemplate;

    private Logger logger = Logger.getLogger(GameRepository.class.getName());

    //task 1
    /**
    db.games.find()
    .limit(10)
    .skip(10)
    **/
    public List<Game> get(Integer limit, Integer offset) {
        // final Pageable pageable = PageRequest.of(offset, limit);
        // Query query = new Query();
        // query.with(pageable);

        //limit = page number
        //offset = page size/no. of docs displayed
        //mongodb.core.query
        Query query = new Query();
            query.skip(limit * offset);
            query.limit(limit);

        //returns a list<doc> without stream
        return mongoTemplate.find(query, Document.class
            , "games")
            .stream()
            .map(t -> Game.fromJSON(t))
            .toList(); 
    }

    //task 2
    /**
    db.games.find()
    .sort({ranking: 1})
    .limit(10)
    .skip(10)
    **/
    public List<Game> getByRanking(Integer limit, Integer offset){
        Query query = new Query(); 
            query.with(Sort.by(Sort.Direction.ASC, "ranking"));
            query.skip(limit * offset);
            query.limit(limit);

        return mongoTemplate.find(query, Document.class
            , "games")
            .stream()
            .map(t -> Game.fromJSON(t))
            .toList(); 
    }

    //task 3, needs to handle non-existent game ids/nulls
    public Game getGameById(Integer gid) {
        Query query = new Query();
        query.addCriteria(Criteria.where("gid").is(gid));
        // System.out.println(query);
        
        Game game = new Game();

        Document d = mongoTemplate.findOne(query, Document.class
        , "games");
        // System.out.println(doc);

        try {
            if (d == null) {
                throw new NullPointerException("Game does not exist");
            }
        } catch (NullPointerException e2) {
            logger.info("Game does not exist");
        }

        game.setGid((d.getInteger("gid") != null? d.getInteger("gid"):0)); 
        game.setName((d.getString("name"))); 
        game.setYear((d.getInteger("year"))); 
        game.setRanking((d.getInteger("ranking"))); 
        game.setUsersRated((d.getInteger("users_rated", 0))); 
        game.setUrl((d.getString("url") != null? d.getString("url"):"")); 
        game.setImage((d.getString("image") != null? d.getString("url"):"")); 

        // System.out.println("Game"+ game);
        return game;

    }
    
}
