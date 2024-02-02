package sg.edu.nus.iss.d26workshop.models;

import org.bson.Document;

import jakarta.json.Json;
import jakarta.json.JsonObject;

public class Comment {

    // {"c_id":"091910b8", "user":"PAYDIRT", "rating":6, "c_text":"A detailed tactical game...", "gid":6228},

    private String user;
    private String text;
    private Integer rating;
    private Integer gid;
    private String cid;

    //average
    public Comment() {
    }

    public Comment(String user, String text, Integer rating, Integer gid, String cid) {
        this.user = user;
        this.text = text;
        this.rating = rating;
        this.gid = gid;
    }

    @Override
    public String toString() {
        return "Comment [user= "
                .concat("" + this.user)
                .concat(", text=")
                .concat(this.text)
                .concat(", rating=")
                .concat("" + this.rating)
                .concat(", gid=")
                .concat("" + this.gid)
                .concat(", cid=")
                .concat("" + this.cid)
                .concat("]");
    }

    public String getUser() { return user; }
    public void setUser(String user) { this.user = user; }
    public String getText() { return text; }
    public void setText(String text) { this.text = text; }
    public Integer getRating() { return rating; }
    public void setRating(Integer rating) { this.rating = rating; }
    public Integer getGid() { return gid; }
    public void setGid(Integer gid) { this.gid = gid; }
    public String getCid() { return cid; }
    public void setCid(String cid) { this.cid = cid; }

    public JsonObject toJSON() {
        return Json.createObjectBuilder()
            .add("user", getUser())
            .add("text", getText() !=null? getText():"")
            .add("rating", getRating())
            .add("gid", getGid())
            .add("cid", getCid())
            .build();
    }

    public static Comment fromJSON(Document d) {
        Comment comment = new Comment();
            comment.setUser(d.getString("user"));
            comment.setText(d.getString("text"));
            comment.setRating(d.getInteger("rating"));
            comment.setGid(d.getInteger("gid"));
            comment.setCid(d.getString("cid"));
        return comment;
    } 
}
