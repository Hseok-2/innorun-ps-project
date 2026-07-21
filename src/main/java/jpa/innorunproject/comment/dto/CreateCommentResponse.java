package jpa.innorunproject.comment.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import jpa.innorunproject.comment.domain.Comment;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class CreateCommentResponse {

    private final Long id;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone = "Asia/seoul")
    private final String content;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone = "Asia/seoul")
    private final LocalDateTime createdAt;

    private CreateCommentResponse(Long id, String content, LocalDateTime createdAt) {
        this.id = id;
        this.content = content;
        this.createdAt = createdAt;
    }

    public static CreateCommentResponse from(Comment comment) {
        return new CreateCommentResponse(
                comment.getId(),
                comment.getContent(),
                comment.getCreatedAt()
        );
    }
}
