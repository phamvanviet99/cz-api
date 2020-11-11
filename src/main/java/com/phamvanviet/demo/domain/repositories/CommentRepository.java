package com.phamvanviet.demo.domain.repositories;

import com.phamvanviet.demo.domain.entities.comment.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Integer> {
    Comment findCommentByIdAndCreatedById(Integer commentId, Integer userId);
}
