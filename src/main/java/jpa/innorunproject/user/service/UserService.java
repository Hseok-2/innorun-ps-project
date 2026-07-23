package jpa.innorunproject.user.service;

import jpa.innorunproject.global.config.PasswordEncoder;
import jpa.innorunproject.user.domain.User;
import jpa.innorunproject.user.dto.*;
import jpa.innorunproject.user.exception.UserInvalidPasswordException;
import jpa.innorunproject.user.exception.UserNotFoundException;
import jpa.innorunproject.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    // 유저 등록
    public CreateUserResponse createUser(CreateUserRequest request) {
        return CreateUserResponse.from(userRepository.save(request.toEntity(request.getPassword())));
    }

    // 유저 전체 조회
    @Transactional(readOnly = true)
    public List<GetUserResponse> getAll() {
        return userRepository.findAll().stream()
                .map(GetUserResponse::from)
                .toList();
    }

    // 유저 단건 조회
    @Transactional(readOnly = true)
    public GetUserResponse getOne(Long userId) {
        return GetUserResponse.from(userRepository.findById(userId)
                .orElseThrow(() -> new UserNotFoundException("해당 유저는 존재하지 않습니다.")));
    }

    // 유저 정보 수정
    public UpdateUserInfoResponse updateUserInfo(Long userId, UpdateUserInfoRequest request) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new UserNotFoundException("해당 유저는 존재하지 않습니다."));

        user.updateInfo(request.getUsername(), request.getEmail());
        userRepository.saveAndFlush(user);

        return UpdateUserInfoResponse.from(user);
    }

    // 유저 비밀번호 수정
    public void updateUserPassword(Long userId,UpdateUserPasswordRequest request) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new UserNotFoundException("해당 유저는 존재하지 않습니다."));

        if (!passwordEncoder.matches(request.getCurrentPassword(), user.getPassword())) {
            throw new UserInvalidPasswordException("현재 비밀번호가 일치하지 않습니다.");
        }
        // 비밀번호 암호화
        String encodedNewPassword = passwordEncoder.encode(user.getPassword());

        user.updatePassword(encodedNewPassword);
    }

    // 유저 삭제
    public void deleteUser(Long userId) {
        userRepository.delete(userRepository.findById(userId)
                .orElseThrow(() -> new UserNotFoundException("해당 유저는 존재하지 않습니다.")));
    }

}
