package sg.edu.nus.iss.d26.repository;

import java.util.List;

import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.TextCriteria;
import org.springframework.data.mongodb.core.query.TextQuery;
import org.springframework.stereotype.Repository;

import sg.edu.nus.iss.d26.models.Comment;

@Repository
public class CommentRepository {

    @Autowired
    private MongoTemplate mongoTemplate;

    public List<Comment> search(List<String> includes, List<String> excludes) {

        return null; //10, 0
    }

    public List<Comment> search(List<String> includes, List<String> excludes, Integer limit) {

        return null; //10, 0
    }
   
    //search in the comments
    public List<Comment> search(List<String> includes, List<String> excludes, Integer limit, Integer offset) {

        //if it's embedded document
        TextCriteria criteria = TextCriteria.forDefaultLanguage()
            .matchingAny(includes.toArray(new String[includes.size()]))
            .notMatchingAny(excludes.toArray(new String[excludes.size()]));

        //joins two collections
        TextQuery textQuery = (TextQuery) TextQuery.queryText(criteria)
            .includeScore("score")
            .sortByScore()
            .skip(offset)
            .limit(limit);

        return mongoTemplate.find(textQuery, Document.class
        ,"comment")
            .stream()
            .map(t -> Comment.fromJSON(t))
            .toList();

    } 
    
}
