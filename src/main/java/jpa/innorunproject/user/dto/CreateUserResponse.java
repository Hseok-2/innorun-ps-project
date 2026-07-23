package jpa.innorunproject.user.dto;

import jpa.innorunproject.user.domain.User;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class CreateUserResponse {

    private final Long id;
    private final String username;
    private final String email;
    private final LocalDateTime createAt;
    private final LocalDateTime updatedAt;

    private CreateUserResponse(Long id, String username, String email, LocalDateTime createAt, LocalDateTime updatedAt) {
        this.id = id;
        this.username = username;
        this.email = email;
        this.createAt = createAt;
        this.updatedAt = updatedAt;
    }

    public static CreateUserResponse from(User user) {
        return new CreateUserResponse(
                user.getId(),
                user.getUsername(),
                user.getEmail(),
                user.getCreatedAt(),
                user.getUpdatedAt()
        );
    }
}
