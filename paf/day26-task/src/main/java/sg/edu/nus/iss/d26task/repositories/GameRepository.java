package sg.edu.nus.iss.d26task.repositories;

import java.util.LinkedList;
import java.util.List;

import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.aggregation.Aggregation;
import org.springframework.data.mongodb.core.aggregation.AggregationResults;
import org.springframework.data.mongodb.core.aggregation.LookupOperation;
import org.springframework.data.mongodb.core.aggregation.MatchOperation;
import org.springframework.data.mongodb.core.aggregation.ProjectionOperation;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.stereotype.Repository;

import sg.edu.nus.iss.d26task.models.Comment;
import sg.edu.nus.iss.d26task.models.Game;

@Repository
public class GameRepository {

    @Autowired
    private MongoTemplate mongoTemplate;

    //marks given for these comments
        /* db.game.aggregate([
        {
            $match: {name: {$regex: "detective", $options: "i"}}
        },
        {
            $project: { _id: "$gid", name: 1, ranking: 1},
        },
        {
            $lookup: {
                from: 'comment',
                foreignField: 'gid',
                localField: '_id',
                as: 'comments'
            }
        }
    ])
    */

    public List<Game> findGamesbyName(String title) {
        MatchOperation matchName = Aggregation.match(
            Criteria.where("name").regex(title, "i")
        );

        ProjectionOperation projectGameFields = Aggregation.project("name", "ranking")
            .and("gid").as("_id");

        LookupOperation joinComments = Aggregation.lookup(
            "comment", "_id", "gid", "comments");

            // System.out.println("Lookup:" + joinComments.toString());
 
        Aggregation pipeline = Aggregation.newAggregation(
            matchName, projectGameFields, joinComments
        );

        //b.son
        AggregationResults<Document> result = mongoTemplate.aggregate(pipeline, "game", Document.class); 
        List<Game> games = new LinkedList<>();
       
        for (Document d: result) {
            int _id = d.getInteger("_id");
            String name = d.getString("name");
            int ranking = d.getInteger("ranking");
            
            List<Comment> comments = new LinkedList<>();

            for (Document e: d.getList("comments", Document.class)) {
                String user = e.getString("user");
                int rating = e.getInteger("rating");
                String text = e.getString("c_text");
                System.out.println("user" + user);
                comments.add(new Comment(user, rating, text));
            }

            games.add(new Game(_id, name, ranking, comments));
        }
        return games;
    }

}
