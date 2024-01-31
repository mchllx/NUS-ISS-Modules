package sg.edu.nus.iss.d28.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Repository;

import com.mongodb.client.result.DeleteResult;
import com.mongodb.client.result.UpdateResult;

import sg.edu.nus.iss.d28.models.Game;

@Repository
public class GameRepo {

    @Autowired
    MongoTemplate mongoTemplate;

    public List<Game> getAllGames() {  
        return mongoTemplate.findAll(Game.class);
    }

    public List<Game> getAllGamesPaginated(int limit, int offset) {
        //method 1 - query statement
        //mongodb.core.query
        Query query = new Query();
            query.skip(limit * offset);
            query.limit(limit);

        //search by page num
        // Pageable pageable = PageRequest.of(limit, offset);
        // query.with(pageable);

        return mongoTemplate.find(query, Game.class);
    }

    public Game createGame(Game game) { 
        return mongoTemplate.save(game);
    }

    public long updateGame(Game game) {
        //check if query exists

        Query query = Query.query(Criteria.where("_id").is(game.getgId
        ()));

        Update updateOps = new Update()
            .set("name", game.getName()) 
            .set("year", game.getYear()) 
            .set("rating", game.getRating())
            .set("userRating", game.getUserRating());

        UpdateResult result = mongoTemplate.updateMulti(query, updateOps, Game.class,
            "games");

        //returns long, no. of records successfully updated
        return result.getModifiedCount();
    }

    public long deleteGame(Game game, int gId) {
        Query query = Query.query(Criteria.where("_id").is(game.getgId
        ()));

        DeleteResult result = mongoTemplate.remove(query, "games");

        return result.getDeletedCount();
    }

    public List<Game> findByName(String name) {
        Query query = new Query();
        query.addCriteria(Criteria.where("name").is(name
        ));

        //returns a list of game entities
        return mongoTemplate.find(query, Game.class);
    }

    public Game findById(Integer id) {
        Query query = new Query();
        query.addCriteria(Criteria.where("_id").is(id));

        return mongoTemplate.findOne(query, Game.class, "games");
    }
    
}
