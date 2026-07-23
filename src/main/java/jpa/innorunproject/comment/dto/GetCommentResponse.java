package jpa.innorunproject.comment.dto;

import jpa.innorunproject.comment.domain.Comment;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class GetCommentResponse {

    private final Long id;
    private final String content;
    private final LocalDateTime createdAt;

    private GetCommentResponse(Long id, String content, LocalDateTime createdAt) {
        this.id = id;
        this.content = content;
        this.createdAt = createdAt;
    }

    public static GetCommentResponse from(Comment comment) {
        return new GetCommentResponse(
                comment.getId(),
                comment.getContent(),
                comment.getCreatedAt()
        );
    }
}
