package jpa.innorunproject.schedule.dto;

import jpa.innorunproject.schedule.domain.Schedule;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class GetScheduleResponse {

    private final Long id;
    private final String title;
    private final String content;
    private final Long userId;
    private final LocalDateTime createAt;
    private final LocalDateTime updatedAt;

    private GetScheduleResponse(Long id, String title, String content, Long userId, LocalDateTime createAt, LocalDateTime updatedAt) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.userId = userId;
        this.createAt = createAt;
        this.updatedAt = updatedAt;
    }

    public static GetScheduleResponse from(Schedule schedule) {
        return new GetScheduleResponse(
                schedule.getId(),
                schedule.getTitle(),
                schedule.getContent(),
                schedule.getUser().getId(),
                schedule.getCreatedAt(),
                schedule.getUpdatedAt()
        );
    }
}
