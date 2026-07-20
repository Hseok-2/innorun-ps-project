package jpa.innorunproject.schedule.dto;

import jakarta.validation.constraints.NotBlank;
import jpa.innorunproject.schedule.domain.Schedule;
import jpa.innorunproject.user.domain.User;
import lombok.Getter;

@Getter
public class CreateScheduleRequest {

    @NotBlank(message = "일정 제목 작성은 필수로 작성해야 합니다")
    public String title;
    @NotBlank(message = "일정 내용 작성은 필수로 작성해야 합니다.")
    public String content;
    public Long userId;

    public Schedule toEntity(User user) {
        return Schedule.builder()
                .title(this.title)
                .content(this.content)
                .user(user)
                .build();
    }
}
