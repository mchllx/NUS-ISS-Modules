package com.example.backend.models;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.Locale;

import javax.swing.text.DateFormatter;

import org.bson.Document;
import org.joda.time.DateTime;

import jakarta.json.Json;
import jakarta.json.JsonArray;
import jakarta.json.JsonObject;

public class Comment {
    
    private Integer pid;
    private String cid;
    private String name;
    private String text;
    private Date timestamp; 

    public Comment() {
    }

    public Comment(Integer pid, String cid, String name, String text, Date timestamp) {
        this.pid = pid;
        this.cid = cid;
        this.name = name;
        this.text = text;
        this.timestamp = timestamp;
    }

    @Override
    public String toString() {
      return "Comment [pid="
        .concat("" + this.pid)
        .concat(", cid=")
        .concat("" + this.cid)
        .concat(", name=")
        .concat("" + this.name)
        .concat(", text=")
        .concat("" + this.text)
        .concat(", timestamp=")
        .concat("" + this.timestamp)
        .concat("]");
    }

    public Integer getPid() { return pid; }
    public void setPid(Integer pid) { this.pid = pid; }
    public String getCid() { return cid; }
    public void setCid(String cid) { this.cid = cid; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public String getText() { return text; }
    public void setText(String text) { this.text = text; }
    public Date getTimestamp() { return timestamp; }
    public void setTimestamp(Date timestamp) { this.timestamp = timestamp; }

    public JsonObject toJSON() {
      return Json.createObjectBuilder()
            .add("pid", pid)
            .add("id", cid)
            .add("name", name)
            .add("text", text)
            .add("timestamp", timestamp.toString())
            .build();
    }

    public Comment fromJSON(JsonObject jsonObj) {
        Comment comment = new Comment();

        comment.setPid(jsonObj.getInt("pid"));
        comment.setCid(jsonObj.getString("id"));
        comment.setName(jsonObj.getString("name"));
        comment.setText(jsonObj.getString("text"));

        String timestamp = jsonObj.getString("timestamp");
        SimpleDateFormat formatter = new SimpleDateFormat("EEE MMM dd HH:mm:ss zzz yyyy", Locale.ENGLISH);

        try {
          Date date = formatter.parse(timestamp);
          comment.setTimestamp(date);;
        } catch (ParseException e4) {
          e4.printStackTrace();
        }

        // System.out.printf(">>>Pid:%d\n", jsonObj.getInt("pid"));
        // System.out.printf(">>>Cid:%s\n", jsonObj.getString("id"));
        // System.out.printf(">>>Name :%s\n", jsonObj.getString("name"));
        // System.out.printf(">>>Text :%s\n", jsonObj.getString("text"));

        return comment;
   }

   public Comment fromBSON(Document d) {
    Comment comment = new Comment();

    comment.setPid(d.getInteger("pid"));
    comment.setCid(d.getString("id"));
    comment.setName(d.getString("name"));
    comment.setText(d.getString("text"));

    String timestamp = d.getString("timestamp");
    SimpleDateFormat formatter = new SimpleDateFormat("EEE MMM dd HH:mm:ss zzz yyyy", Locale.ENGLISH);

    try {
      Date date = formatter.parse(timestamp);
      comment.setTimestamp(date);;
    } catch (ParseException e4) {
      e4.printStackTrace();
    }

    // System.out.printf(">>>Pid:%d\n", comment.pid);
    // System.out.printf(">>>Cid:%s\n", comment.cid);
    // System.out.printf(">>>Name :%s\n", comment.name);
    // System.out.printf(">>>Text :%s\n", comment.text);
    // System.out.printf(">>>Published :%s\n", comment.timestamp.toString());

    return comment;
}
    
}
