package com.example.task1.controller;

import com.example.task1.domain.Comment;
import com.example.task1.service.CommentService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/comments")
public class CommentController {

    private final CommentService commentService;

    public CommentController(CommentService commentService) {
        this.commentService = commentService;
    }

    @PostMapping("/add")
    public ResponseEntity<Comment> addComment(@RequestParam Long taskId, @RequestParam String text) {
        return ResponseEntity.ok(commentService.addComment(taskId, text));
    }

    @PutMapping("/update")
    public ResponseEntity<Comment> updateComment(@RequestParam Long taskId, @RequestParam String text) {
        return ResponseEntity.ok(commentService.updateComment(taskId, text));
    }

    @DeleteMapping("/delete")
    public ResponseEntity<String> deleteComment(@RequestParam Long commentId) {
        commentService.deleteComment(commentId);
        return ResponseEntity.ok("Comment deleted successfully");
    }

    @GetMapping("/task")
    public ResponseEntity<List<Comment>> getCommentsByTask(@RequestParam Long taskId) {
        return ResponseEntity.ok(commentService.getCommentsByTask(taskId));
    }
}
