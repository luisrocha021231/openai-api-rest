package com.luisrocharo.openaiapi.portfolio.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.luisrocharo.openaiapi.portfolio.entities.CommentsEntity;
import com.luisrocharo.openaiapi.portfolio.services.CommentsService;

@RestController
@RequestMapping("/comments")
public class CommentsController {
    
    @Autowired
    private CommentsService commentsService;

    @PostMapping
    public ResponseEntity<CommentsEntity> createComment( @RequestBody CommentsEntity comment, BindingResult bindingResult) {
        if(bindingResult.hasErrors()) {
            return ResponseEntity.badRequest().build();
        } else if(comment.getComment() == null || comment.getComment().isBlank()) {
            return ResponseEntity.badRequest().build();
        } else {
            CommentsEntity createdComment = commentsService.saveComment(comment);
            return ResponseEntity.ok(createdComment);
        }
    }

    @GetMapping
    public ResponseEntity<List<CommentsEntity>> getComments() {
        List<CommentsEntity> comments = commentsService.getFourLatestComments();
        return ResponseEntity.ok(comments);
    }
}
