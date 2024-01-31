package sg.edu.nus.iss.d28.services;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import sg.edu.nus.iss.d28.exception.GameNotFoundException;
import sg.edu.nus.iss.d28.models.EditedComment;
import sg.edu.nus.iss.d28.models.Game;
import sg.edu.nus.iss.d28.models.Review;
import sg.edu.nus.iss.d28.repository.GameRepo;
import sg.edu.nus.iss.d28.repository.ReviewRepo;

@Service
public class GameService {

    @Autowired
    GameRepo gameRepo;

    @Autowired
    ReviewRepo reviewRepo;

    public Game findById(int gId) {
        return gameRepo.findById(gId);
    }

    public List<Game> getAllGames() {
        return gameRepo.getAllGames();
    }

    public List<Game> getAllGamesPaginated(int limit, int offset) {
        return gameRepo.getAllGamesPaginated(limit, offset);
    }

    public Game createGame(Game game) {
        return gameRepo.createGame(game);
    }

    public Long updateGame(Game game) {
        return gameRepo.updateGame(game);
    }

    public Long deleteGame(Game game, int gId) {
        return gameRepo.deleteGame(game, gId);
    }

    public List<Game> findByName(String name) {
        return gameRepo.findByName(name);
    }

    //write API in controller
    public Review createReview(Review review) throws GameNotFoundException{
        Game game = gameRepo.findById(review.getGameId());

        if (game.equals(null)) {
            throw new GameNotFoundException("No game record found");
        }

        review.setGameName(game.getName());
        review.setPosted(LocalDateTime.now());

        return reviewRepo.createReview(review);
    }

    //write API in controller
    public Review getReviewById(Integer reviewId) {
        return reviewRepo.getReviewById(reviewId);
    }

    //write API in controller
    public Long updateReview(EditedComment editedComment) {
        Review result = reviewRepo.getReviewById(editedComment.getcId());
        List<EditedComment> list = result.getEdited();

        //check if there is existing comments
        if (result.getEdited().equals(null)) {
            list = new ArrayList<>();
            result.setEdited(list);
        }

        //assign edited comment values into object
        EditedComment comment = new EditedComment();
            comment.setComment(result.getComment());
            comment.setRating(result.getRating());
            comment.setPosted(result.getPosted());

        return reviewRepo.updateReview(result);
    }

    public Long deleteReview(Review review) {
        return reviewRepo.deleteReview(review);
    }
    
}
