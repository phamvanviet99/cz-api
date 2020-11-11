package com.phamvanviet.demo.app.controllers;

import com.phamvanviet.demo.app.dtos.CommentDTO;
import com.phamvanviet.demo.domain.services.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1/comment")
public class CommentController {
    @Autowired
    private CommentService commentService;

    @PostMapping
    public ResponseEntity<?> create(@RequestBody CommentDTO commentDTO, @RequestHeader("accessToken") String accessToken) {
        return commentService.create(commentDTO, accessToken);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@RequestBody CommentDTO commentDTO, @RequestHeader("accessToken") String accessToken, @PathVariable("id") Integer id) {
        return commentService.update(commentDTO, accessToken, id);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@RequestHeader("accessToken") String accessToken, @PathVariable("id") Integer id) {
        return commentService.delete(accessToken, id);
    }
}
