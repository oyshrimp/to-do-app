package com.sparta.todoapp.controller;

import com.sparta.todoapp.dto.CommentRequest;
import com.sparta.todoapp.entity.Comment;
import com.sparta.todoapp.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/schedule/comments")
public class CommentController {

    private final CommentService commentService;

    @PostMapping
    public ResponseEntity<Void> createComment(@RequestBody CommentRequest commentRequest) {
        commentService.createComment(commentRequest);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Comment> updateComment(@PathVariable Long id, @RequestBody CommentRequest commentRequest, @RequestParam String password) {
        Comment updatedComment = commentService.updateComment(id, commentRequest, password);
        return ResponseEntity.ok(updatedComment);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteComment(@PathVariable Long id, @RequestParam String password) {
        commentService.deleteComment(id, password);
        return ResponseEntity.ok().build();

    }

}