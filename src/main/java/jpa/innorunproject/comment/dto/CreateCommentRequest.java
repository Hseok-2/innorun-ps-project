package jpa.innorunproject.comment.dto;

import jakarta.validation.constraints.NotBlank;
import jpa.innorunproject.comment.domain.Comment;
import jpa.innorunproject.schedule.domain.Schedule;
import jpa.innorunproject.user.domain.User;
import lombok.Getter;

@Getter
public class CreateCommentRequest {

    @NotBlank(message = "댓글 내용을 필수로 작성해야합니다.")
    public String content;
    public Long userId;

    public Comment toEntity(User user, Schedule schedule) {
        return Comment.builder()
                .content(this.content)
                .user(user)
                .schedule(schedule)
                .build();
    }
}
