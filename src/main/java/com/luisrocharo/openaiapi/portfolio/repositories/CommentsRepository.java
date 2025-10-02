package com.luisrocharo.openaiapi.portfolio.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.luisrocharo.openaiapi.portfolio.entities.CommentsEntity;

@Repository
public interface CommentsRepository extends JpaRepository<CommentsEntity, Long>{

    List<CommentsEntity> findTop4ByOrderByCreatedAtDesc();
}
