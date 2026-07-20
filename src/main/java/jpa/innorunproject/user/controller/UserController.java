package jpa.innorunproject.user.controller;

import jakarta.validation.Valid;
import jpa.innorunproject.user.dto.*;
import jpa.innorunproject.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    // 유저 등록
    @PostMapping
    public ResponseEntity<CreateUserResponse> createUser(@Valid @RequestBody CreateUserRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(userService.createUser(request));
    }

    // 유저 전체 조회
    @GetMapping
    public ResponseEntity<List<GetUserResponse>> getAll() {
        return ResponseEntity.ok(userService.getAll());
    }

    // 유저 단건 조회
    @GetMapping("/{userId}")
    public ResponseEntity<GetUserResponse> getOne(@PathVariable Long userId) {
        return ResponseEntity.ok(userService.getOne(userId));
    }

    // 유저 수정
    @PutMapping("/{userId}")
    public ResponseEntity<UpdateUserResponse> updateUser(@PathVariable Long userId, @Valid @RequestBody UpdateUserRequest request) {
        return ResponseEntity.ok(userService.updateUser(userId, request));
    }

    // 유저 삭제
    @DeleteMapping("/{userId}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long userId) {
        userService.deleteUser(userId);
        return ResponseEntity.noContent().build();
    }
}
