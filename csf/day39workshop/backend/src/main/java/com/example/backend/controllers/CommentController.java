package com.example.backend.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.backend.exceptions.MongoAddCommentException;
import com.example.backend.models.Comment;
import com.example.backend.models.Pokemon;
import com.example.backend.service.CommentService;
import com.example.backend.service.PokemonService;

import jakarta.json.Json;
import jakarta.json.JsonArray;
import jakarta.json.JsonObject;
import jakarta.json.JsonReader;
import jakarta.json.JsonValue;

import java.io.StringReader;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.UUID;
import java.util.logging.Logger;

import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

@Controller
@CrossOrigin(origins={"http://localhost:4200", "https://vttpb4-michelle-lim.up.railway.app", "http://localhost:8080"})
@RequestMapping(path="/api/comment", produces=MediaType.APPLICATION_JSON_VALUE)
public class CommentController {

    @Autowired
    private CommentService commentSvc;

    // POST http://localhost:8080/api/comment/{cid}
    @PostMapping(path="/{id}")
    @ResponseBody
    public ResponseEntity<Comment> postComment(@PathVariable String id, @RequestBody String payload) {

        System.out.println("payload" + payload);

        JsonReader jr = Json.createReader(new StringReader(payload));
        JsonObject jsonObj = jr.readObject().getJsonObject("comment");

        System.out.println("json" + jsonObj.toString());

        Integer pid = Integer.parseInt(jsonObj.getString("pid"));
        // String id = UUID.randomUUID().toString().substring(0,8);
        String cid = jsonObj.getString("id");
        String name = jsonObj.getString("name");
        String text = jsonObj.getString("text");
        Date timestamp = new Date();

        Comment comment = new Comment(pid, cid, name, text, timestamp);
            commentSvc.postComment(comment);
         
        System.out.println(">>>Successfully posted:" + comment.toString());

        return ResponseEntity
        .status(HttpStatus.OK)
        .contentType(MediaType.APPLICATION_JSON)
        .body(comment);
    }

    // GET /api/comment?pid=123
    @GetMapping
    @ResponseBody
    public ResponseEntity<List<Comment>> getCommentByPid(@RequestParam Integer pid) {

        System.out.println("pid" + pid);

        List<Comment> results = commentSvc.getCommentByPid(pid);

        System.out.println(">>>retrieve comments:" + results.toString());

        if (results.size() <= 0) {
            return ResponseEntity
            .status(HttpStatus.BAD_REQUEST)
            .contentType(MediaType.APPLICATION_JSON)
            .body(new LinkedList<Comment>());
        }

        return ResponseEntity
        .status(HttpStatus.OK)
        .contentType(MediaType.APPLICATION_JSON)
        .body(results);
    }






}
