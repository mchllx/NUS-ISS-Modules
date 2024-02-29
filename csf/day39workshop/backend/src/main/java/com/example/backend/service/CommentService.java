package com.example.backend.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.backend.models.Comment;
import com.example.backend.repositories.CommentRepository;

@Service
public class CommentService {

    // @Transactional
    @Autowired
    private CommentRepository commentRepo;

    public void postComment(Comment comment) {
        commentRepo.postComment(comment);
    }

    public List<Comment> getCommentByPid(Integer pid) {
        return commentRepo.getCommentByPid(pid);
    }

    public void updateComment(String id) {
        commentRepo.updateComment(id);
    }

    public void deleteComment(String id) {
        commentRepo.deleteComment(id);
    }
    
}
