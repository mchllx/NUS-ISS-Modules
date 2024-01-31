package sg.edu.nus.iss.d28.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Repository;

import com.mongodb.client.result.DeleteResult;
import com.mongodb.client.result.UpdateResult;

import sg.edu.nus.iss.d28.models.Review;

@Repository
public class ReviewRepo {

    @Autowired
    MongoTemplate mongoTemplate;

    public Review getReviewById(int reviewId) {
        Query query = new Query();
        query.addCriteria(Criteria.where("_id").is(reviewId));
        //where _is = ?
        //reviewId is = ?

        return mongoTemplate.findOne(query, Review.class);
    }

    public Review createReview(Review review) {
        return mongoTemplate.insert(review, "reviews");
    }

    public long updateReview(Review review) {
        Query query = new Query();
        query.addCriteria(Criteria.where("_id").is(review.getReviewId()
        ));

        Update updateOps = new Update()
            .set("rating", review.getRating())
            .set("comment", review.getComment())
            .set("posted", review.getPosted())
            .set("edited", review.getEdited());

        UpdateResult result = mongoTemplate.updateMulti(query, updateOps, Review.class);
        return result.getMatchedCount();
    }

    public long deleteReview(Review review) {
        Query query = new Query();
        query.addCriteria(Criteria.where("_id").is(review.getReviewId()
        ));

        DeleteResult result = mongoTemplate.remove(query, "games");

        return result.getDeletedCount();
    }

    
    
}
