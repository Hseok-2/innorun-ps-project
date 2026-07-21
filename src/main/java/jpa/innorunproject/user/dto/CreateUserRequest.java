package jpa.innorunproject.user.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import jpa.innorunproject.user.domain.User;
import lombok.Getter;

@Getter
public class CreateUserRequest {

    @NotBlank(message = "이름은 필수로 작성해야 합니다.")
    @Size(max = 20, message = "이름은 최대 20자까지 입력 가능합니다.")
    public String username;

    @NotBlank(message = "이메일은 필수로 작성해야 합니다.")
    @Email(message = "올바른 이메일 형식이 아닙니다.")
    public String email;

    @NotBlank(message = "비밀번호는 필수로 작성해야 합니다.")
    public String password;

    public User toEntity(String encodedPassword) {
        return User.builder()
                .username(this.username)
                .password(encodedPassword)
                .email(this.email)
                .build();
    }
}
