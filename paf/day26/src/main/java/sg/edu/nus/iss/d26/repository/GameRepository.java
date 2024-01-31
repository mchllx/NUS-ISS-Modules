package sg.edu.nus.iss.d26.repository;

import java.util.List;

import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import sg.edu.nus.iss.d26.models.Game;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

@Repository
public class GameRepository {

    @Autowired
    private MongoTemplate mongoTemplate;

    public List<Game> search(Integer limit, Integer offset) {

        //setting up params for query
        final PageRequest pageable = PageRequest.of(limit, offset);
        Query query = new Query().with(pageable);

        //game = file name, returns a list
        return mongoTemplate.find(query, Document.class, 
            "game")
            .stream()
            .map(t -> Game.fromJSON(t))
            .toList();
    }

    public List<Game> getGamesByRank() {
        Query query = new Query();
        query.with(Sort.by(Sort.Direction.ASC, "ranking"));

        return mongoTemplate.find(query, Document.class, 
            "game")
            .stream()
            .map(t -> Game.fromJSON(t))
            .toList();
    }

    public Game getGameDetails(Integer gid) {
        Query query = new Query();
        query.addCriteria(Criteria.where("gid").is(gid));

        return mongoTemplate.findOne(query, Game.class, 
            "game");

    }
 
}
