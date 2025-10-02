package com.luisrocharo.openaiapi.portfolio.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.luisrocharo.openaiapi.portfolio.entities.CommentsEntity;
import com.luisrocharo.openaiapi.portfolio.repositories.CommentsRepository;

@Service
public class CommentsService {
    
    @Autowired
    private CommentsRepository commentsRepository;

    public List<CommentsEntity> getFourLatestComments() {
        return commentsRepository.findTop4ByOrderByCreatedAtDesc();
    }

    public CommentsEntity saveComment(CommentsEntity comment) {
        return commentsRepository.save(comment);
    }
}
