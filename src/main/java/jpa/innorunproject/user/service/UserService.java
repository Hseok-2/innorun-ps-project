package jpa.innorunproject.user.service;

import jakarta.validation.Valid;
import jpa.innorunproject.user.domain.User;
import jpa.innorunproject.user.dto.*;
import jpa.innorunproject.user.exception.UserNotFoundException;
import jpa.innorunproject.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.jspecify.annotations.Nullable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    // 유저 등록
    public CreateUserResponse createUser(CreateUserRequest request) {
        return CreateUserResponse.from(userRepository.save(request.toEntity()));
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

    // 유저 수정
    public UpdateUserResponse updateUser(Long userId, UpdateUserRequest request) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new UserNotFoundException("해당 유저는 존재하지 않습니다."));

        user.update(request.getUsername(), request.getEmail());
        userRepository.saveAndFlush(user);

        return UpdateUserResponse.from(request.toEntity());
    }

    // 유저 삭제
    public void deleteUser(Long userId) {
        userRepository.delete(userRepository.findById(userId)
                .orElseThrow(() -> new UserNotFoundException("해당 유저는 존재하지 않습니다.")));
    }
}
