package sg.edu.nus.iss.d28.models;

import java.io.Serializable;
import java.time.LocalDateTime;

import org.springframework.data.annotation.Id;

import jakarta.json.Json;
import jakarta.json.JsonObject;
import jakarta.json.JsonObjectBuilder;

public class EditedComment implements Serializable{

    @Id
    private Integer cId;
    private Integer rating;
    private String comment;
    private LocalDateTime posted;

    @Override
    public String toString() {
        return "EditedComment [cId=" + cId + ", rating=" + rating + ", comment=" + comment + ", posted=" + posted + "]";
    }

    public EditedComment() {
    }

    public EditedComment(Integer cId, Integer rating, String comment, LocalDateTime posted) {
        this.cId = cId;
        this.rating = rating;
        this.comment = comment;
        this.posted = posted;
    }

    public Integer getcId() { return cId; }
    public void setcId(Integer cId) { this.cId = cId; }
    public Integer getRating() { return rating; }
    public void setRating(Integer rating) { this.rating = rating; }
    public String getComment() { return comment; }
    public void setComment(String comment) { this.comment = comment; }
    public LocalDateTime getPosted() { return posted; }
    public void setPosted(LocalDateTime posted) { this.posted = posted; }

    public JsonObject toJSON() {
        return Json.createObjectBuilder()
            .add("cId", this.getcId())
            .add("rating", this.getRating())
            .add("comment", this.getComment())
            .add("posted", this.getPosted().toString())
            .build();
    }

    public JsonObjectBuilder toJSONBuilder() {
        return Json.createObjectBuilder()
            .add("cId", this.getcId())
            .add("rating", this.getRating())
            .add("comment", this.getComment())
            .add("posted", this.getPosted().toString());
    }
    
}
