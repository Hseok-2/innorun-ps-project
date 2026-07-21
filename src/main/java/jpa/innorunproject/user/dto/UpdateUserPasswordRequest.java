package jpa.innorunproject.user.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;

@Getter
public class UpdateUserPasswordRequest {

    @NotBlank(message = "현재 비밀번호를 입력해주세요.")
    public String currentPassword;
    @NotBlank(message = "새로운 비밀번호를 입력해주세요.")
    public String newPassword;
}
