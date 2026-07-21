package jpa.innorunproject.comment.controller;

import jakarta.validation.Valid;
import jpa.innorunproject.comment.dto.CreateCommentRequest;
import jpa.innorunproject.comment.dto.CreateCommentResponse;
import jpa.innorunproject.comment.dto.GetCommentResponse;
import jpa.innorunproject.comment.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/schedules/{scheduleId}/comments")
public class CommentController {

    private final CommentService commentService;

    // 댓글 등록
    @PostMapping
    public ResponseEntity<CreateCommentResponse> createComment(@PathVariable Long scheduleId, @Valid @RequestBody CreateCommentRequest request) {
        return ResponseEntity.ok(commentService.createComment(scheduleId, request));
    }

    // 댓글 전체 조회
    @GetMapping
    public ResponseEntity<List<GetCommentResponse>> getAll(@PathVariable Long scheduleId) {
        return ResponseEntity.ok(commentService.getAll(scheduleId));
    }
}
