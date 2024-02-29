package com.example.backend.repositories;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.aggregation.AddFieldsOperation;
import org.springframework.data.mongodb.core.aggregation.Aggregation;
import org.springframework.data.mongodb.core.aggregation.AggregationOperation;
import org.springframework.data.mongodb.core.aggregation.AggregationOptions;
import org.springframework.data.mongodb.core.aggregation.AggregationResults;
import org.springframework.data.mongodb.core.aggregation.LimitOperation;
import org.springframework.data.mongodb.core.aggregation.MatchOperation;
import org.springframework.data.mongodb.core.aggregation.ProjectionOperation;
import org.springframework.data.mongodb.core.aggregation.SkipOperation;
import org.springframework.data.mongodb.core.aggregation.SortOperation;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import com.example.backend.models.Comment;

@Repository
public class CommentRepository {

    // @Value("${apikey}")
    // private String apiKey;

    @Autowired
    MongoTemplate template;

    final String COLLECTION = "comments";

    public void postComment(Comment comment) {
        Document d = Document.parse(comment.toJSON().toString());

        System.out.println(">>>Inserting record");
        template.insert(d, COLLECTION);
    }

    // db.getCollection("comments").find()
    // .sort({timestamp: -1})
    // .limit(4)
    // public List<Comment> getCommentByPid(Integer pid) {
    //     Query query = Query.query(Criteria.where("pid").is(pid));
    //         query.with(Sort.by(Sort.Direction.DSC, "timestamp"))
    //         .limit(4)
    //         .skip(0);

    //     List<Document> comments = template.find(query, Document.class, COLLECTION);

    //     List<Comment> results = new ArrayList<>(); 

    //     if (comments.size() <= 0) {
    //         System.out.println(">>> No comments found");
    //     } else {
    //         for (Document d: comments) {
    //             Comment comment = new Comment();
    //             comment = comment.fromBSON(d);
    //             // System.out.println(">>>comment:" + comment.toString());

    //             results.add(comment);
    //         }

    //         System.out.println(">>>comment:" + results.toString());
    //     }
 
    //     return results; 
    // }

//     db.comments.aggregate([
//   {
//     $match: { pid: 285}
//   },
//   {
//     $addFields: {
//       date: {
//         $dateFromString: {
//           dateString: {
//             $replaceAll: {
//               input: "$timestamp",
//               find: "SGT",
//               replacement: "+08:00" // Replace SGT with the corresponding UTC offset
//             }}}}}
//   },
//   {
//     $sort: {
//       date: -1, // Sort by date in descending order
//       timestamp: -1 // Optionally sort by timestamp in descending order
//     }
//   },
//   {
//     $limit: 4
//   },
//   {
//     $skip: 0
//   }
// ]);
    public List<Comment> getCommentByPid(Integer pid) {

        ProjectionOperation projectOps = Aggregation
            .project("_id", "pid", "id", "name", "text", "timestamp"); 
           
        MatchOperation matchOps = Aggregation
            .match(Criteria.where("pid").is(pid));

        AddFieldsOperation addFieldOps = Aggregation
            .addFields()
            .addField("date")
            .withValue(new Document("$dateFromString",
            new Document("dateString",
            new Document("$replaceAll",
            new Document("input", "$timestamp")
            .append("find", "SGT")
            .append("replacement", "+08:00")))))
            .build();
            
        SortOperation sortOps = Aggregation
            .sort(Sort.by(Direction.DESC, "date", "timestamp"));

        SkipOperation skipOps = Aggregation
            .skip(0);
        
        LimitOperation limitOps = Aggregation
            .limit(4);
            
        Aggregation pipeline = Aggregation.newAggregation(
            projectOps, matchOps, addFieldOps, sortOps, skipOps, limitOps);

        AggregationResults<Document> aggregation = template.aggregate(pipeline, COLLECTION, Document.class);
        List<Document> comments = aggregation.getMappedResults();

        List<Comment> results = new ArrayList<>(); 

        if (comments.size() <= 0) {
            System.out.println(">>> No comments found");
        } else {
            for (Document d: comments) {
                Comment comment = new Comment();
                comment = comment.fromBSON(d);
                // System.out.println(">>>comment:" + comment.toString());

                results.add(comment);
            }

            System.out.println(">>>comment:" + results.toString());
        }
 
        return results;
    }

    public void updateComment(String id) {

    } 
 
    public void deleteComment(String id) {

    }
    
}
