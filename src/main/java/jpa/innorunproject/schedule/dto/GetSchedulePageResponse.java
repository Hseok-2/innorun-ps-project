package jpa.innorunproject.schedule.dto;

import jpa.innorunproject.schedule.domain.Schedule;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class GetSchedulePageResponse {

    private final String title;
    private final String content;
    private final int commentCount;
    private final LocalDateTime createdAt;
    private final LocalDateTime updatedAt;
    private final String username;

    private GetSchedulePageResponse(String title, String content, int commentCount, LocalDateTime createdAt, LocalDateTime updatedAt, String username) {
        this.title = title;
        this.content = content;
        this.commentCount = commentCount;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.username = username;
    }

    public static GetSchedulePageResponse from(Schedule schedule, int commentCount) {
        return new GetSchedulePageResponse(
                schedule.getTitle(),
                schedule.getContent(),
                commentCount,
                schedule.getCreatedAt(),
                schedule.getUpdatedAt(),
                schedule.getUser().getUsername()
        );
    }
}
