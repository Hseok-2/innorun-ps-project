package jpa.innorunproject.schedule.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;

@Getter
public class UpdateScheduleRequest {

    @NotBlank(message = "일정 수정 시 제목 작성은 필수입니다.")
    public String title;
    @NotBlank(message = "일정 수정 시 제목 내용은 필수입니다.")
    public String content;
    public Long userId;
}
