package sg.edu.nus.iss.d26workshop.repositories;

import java.util.List;

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
import sg.edu.nus.iss.d26workshop.models.Game;

@Repository
public class GameRepository {

    @Autowired
    MongoTemplate mongoTemplate;

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

        Document doc = mongoTemplate.findOne(query, Document.class
        , "games");
        // System.out.println(doc);

        Game game = new Game();
        return game.fromJSON(doc);
    }
    
}
