package sg.edu.nus.iss.d26workshop.repositories;

import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.logging.Logger;

import org.bson.Document;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.aggregation.DocumentOperators.Rank;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.format.datetime.DateFormatter;
import org.springframework.stereotype.Repository;

import com.mongodb.client.result.UpdateResult;
import com.mongodb.internal.connection.QueryResult;

import jakarta.json.Json;
import jakarta.json.JsonObject;
import jakarta.json.JsonObjectBuilder;
import sg.edu.nus.iss.d26workshop.exception.GameNotFoundException;
import sg.edu.nus.iss.d26workshop.models.Comment;
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
                throw new GameNotFoundException("Game does not exist");
            }
        } catch (GameNotFoundException e2) {
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

    //task1, day27
//     db.reviews.updateOne(
//     {
//         gid: 1
//     },
//     {
//         $set: {user: "test", rating: 1, c_text: "bad", posted: new Date(), name: "Tester"}
//     },
//     {upsert: true}
// )

    public void updateCommentById(Integer gid, JsonObject results) {

        Query query = new Query()
            .addCriteria(Criteria.where("gid")
            .is(gid)
            );
        
        Update update = new Update()
            .set("user", results.getString("user"))
            .set("rating", results.getInt("rating"))
            .set("c_text", results.getString("comment"))
            .set("gid", results.getInt("id"))
            .set("posted", results.getString("posted"))
            .set("name", results.getString("name"));

        UpdateResult updateResult = mongoTemplate
            .updateFirst(query, update, Document.class, "comments");

        System.out.printf(">>>Documents updated: %d\n", updateResult.getModifiedCount());

    }

    // public void upsertCommentById(Integer gid, String cid, JsonObject results) {
    //     if (cid == null) {
    //         cid = new ObjectId().toString();
    //     }

    //     Query query = new Query()
    //         .addCriteria(Criteria.where("gid")
    //         .is(gid).and(cid)
    //         );
        
    //     Update update = new Update()
    //         .set("user", results.getString("user"))
    //         .set("rating", results.getInt("rating"))
    //         .set("c_text", results.getString("comment"))
    //         .set("gid", results.getInt("id"))
    //         .set("posted", results.getString("posted"))
    //         .set("name", results.getString("name"));

    //     UpdateResult updateResult = mongoTemplate
    //         .updateFirst(query, update, Document.class, "comments");

    //     System.out.printf(">>>Documents inserted: %s\n", updateResult.getUpsertedId());
    //     System.out.printf(">>>Documents updated: %d\n", updateResult.getModifiedCount());

    // }

    /** "_id" : ObjectId("65b244e23e700314feb5e679"), "c_id" : "091910bd", "user" : "Gamesrosco",
    "rating" : NumberInt(7), "c_text" : "An engaging game with cute bug cardboard pieces for 2-5 year olds with 3 levels of play.  Good for practicing identification of numbers, colours and shapes all in one game.", "gid" : NumberInt(132322)**/
    public List<Comment> getCommentById(Integer gid) {
        Query query = new Query()
            .addCriteria(Criteria.where("gid").is(gid));

        List<Comment> comments = new ArrayList<>();


        List<Document> docs = mongoTemplate
            .find(query, Document.class, "comments");


        for (Document d: docs) {
            String cid = d.getString("c_id");
            String user = d.getString("user");
            int rating = d.getInteger("rating");
            String text = d.getString("c_text");
            gid = d.getInteger("gid");

            Comment comment = new Comment();
                comment.setUser(user);
                comment.setRating(rating);
                comment.setText(text);
                comment.setGid(gid);
                comment.setCid(cid);

            comments.add(comment);
        }
            
        return comments;
    }

    public JsonObject getCommentByCId(String cid) {
        Query query = new Query()
            .addCriteria(Criteria.where("cid").is(cid));
        
        Document d = mongoTemplate.findOne(query, Document.class, "comments");

        cid = d.getString("c_id");
        String user = d.getString("user");
        int rating = d.getInteger("rating");
        String text = d.getString("c_text");
        Integer gid = d.getInteger("gid");
        String posted = d.getString("posted");
        String name = d.getString("name");
        boolean edited = d.getBoolean("edited");

        // DateFormatter df = new DateFormatter();
        // Date date = df.parse(rawDate, Locale.getDefault());

        JsonObjectBuilder objBuilder = Json.createObjectBuilder()
            .add("user", user)
            .add("rating", rating)
            .add("id", gid)
            .add("comment", text)
            .add("posted", posted)
            .add("name", name)
            .add("edited", edited);

        JsonObject obj = objBuilder.build();

        return obj;
    }
    
}
