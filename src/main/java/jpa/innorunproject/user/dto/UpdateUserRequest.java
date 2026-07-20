package jpa.innorunproject.user.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jpa.innorunproject.user.domain.User;
import lombok.Getter;

@Getter
public class UpdateUserRequest {

    @NotBlank(message = "유저 이름 수정 시 필수로 작성해야 합니다.")
    public String username;
    @NotBlank(message = "유저 이메일 수정 시 필수로 작성해야 합니다.")
    @Email(message = "올바른 이메일 형식이 아닙니다.")
    public String email;

    public User toEntity() {
        return User.builder()
                .username(this.username)
                .email(this.email)
                .build();
    }
}
