package jpa.innorunproject.schedule.dto;

import jpa.innorunproject.schedule.domain.Schedule;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class CreateScheduleResponse {

    private final Long id;
    private final String title;
    private final String content;
    private final LocalDateTime createAt;
    private final LocalDateTime updatedAt;

    private CreateScheduleResponse(Long id, String title, String content, LocalDateTime createAt, LocalDateTime updatedAt) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.createAt = createAt;
        this.updatedAt = updatedAt;
    }

    public static CreateScheduleResponse from(Schedule schedule) {
        return new CreateScheduleResponse(
                schedule.getId(),
                schedule.getTitle(),
                schedule.getContent(),
                schedule.getCreatedAt(),
                schedule.getUpdatedAt()
        );
    }
}
