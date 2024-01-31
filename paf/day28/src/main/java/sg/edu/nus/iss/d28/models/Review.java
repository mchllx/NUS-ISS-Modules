package sg.edu.nus.iss.d28.models;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import jakarta.json.Json;
import jakarta.json.JsonArrayBuilder;
import jakarta.json.JsonObject;
import jakarta.json.JsonObjectBuilder;

@Document(collection="reviews")
public class Review extends EditedComment{

    @Id
    private Integer reviewId;
    private Integer gameId;
    private String gameName;
    private String user;

    private List<EditedComment> edited;
    //rating, comment, posted

    public Review(Integer reviewId, Integer gameId, String gameName, String user, List<EditedComment> edited) {
        this.reviewId = reviewId;
        this.gameId = gameId;
        this.gameName = gameName;
        this.user = user;
        this.edited = edited;
    }
    public Review(Integer cId, Integer rating, String comment, LocalDateTime posted, Integer reviewId, Integer gameId,
            String gameName, String user, List<EditedComment> edited) {
        super(cId, rating, comment, posted);
        this.reviewId = reviewId;
        this.gameId = gameId;
        this.gameName = gameName;
        this.user = user;
        this.edited = edited;
    }
    
    public Review() {
    }

    public Integer getReviewId() { return reviewId; }
    public void setReviewId(Integer reviewId) { this.reviewId = reviewId; }
    public Integer getGameId() { return gameId; }
    public void setGameId(Integer gameId) { this.gameId = gameId; }
    public String getGameName() { return gameName; }
    public void setGameName(String gameName) { this.gameName = gameName; } 
    public String getUser() { return user; }
    public void setUser(String user) { this.user = user; }
    public List<EditedComment> getEdited() { return edited; }
    public void setEdited(List<EditedComment> edited) { this.edited = edited; }

    public JsonObject toJSON() {
        return Json.createObjectBuilder()
            .add("reviewId", getReviewId())
            .add("gameId", getGameId())
            .add("gameName", getGameName())
            .add("user", getUser())
            .add("cId", getcId())
            .add("comment", getComment())
            .add("posted", getPosted().toString())
            .build();
    }

    public JsonObject toJSONEdited() {
        boolean hasEditedComment = false;

        //check if edited comments are null
        if (this.getEdited() != null) {
           List<JsonObjectBuilder> editComments = this.getEdited()
            .stream()
            .map(t -> t.toJSONBuilder())
            .toList();

            if (editComments.size() > 0) {
                hasEditedComment = true;
            }
        }

        //transforms one comment
        return Json.createObjectBuilder()
            .add("gId", getGameId())
            .add("comment", getComment())
            .add("rating", getRating())
            .add("posted", getPosted().toString())
            .add("user", getUser())
            .add("gameName", getGameName())
            .add("edited", hasEditedComment)
            .build();
    }

    public JsonObject toJSONEditedList() {
        JsonArrayBuilder arrBuilder = Json.createArrayBuilder();
        List<JsonObjectBuilder> editComments = this.getEdited()
            .stream()
            .map(t -> t.toJSONBuilder())
            .toList();

        for (JsonObjectBuilder j : editComments) {
            arrBuilder.add(j);
        }

        //transforms one comment obj to a list of edited comment (array)
        return Json.createObjectBuilder()
        .add("gId", getGameId())
        .add("comment", getComment())
        .add("rating", getRating())
        .add("posted", getPosted().toString())
        .add("user", getUser())
        .add("gameName", getGameName())
        .add("edited", arrBuilder)
        .build();
    } 
}
