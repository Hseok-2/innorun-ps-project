package jpa.innorunproject.schedule.controller;

import jakarta.validation.Valid;
import jpa.innorunproject.schedule.dto.*;
import jpa.innorunproject.schedule.service.ScheduleService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/schedules")
public class ScheduleController {

    private final ScheduleService scheduleService;

    // 일정 등록
    @PostMapping
    public ResponseEntity<CreateScheduleResponse> createSchedule(@Valid @RequestBody CreateScheduleRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(scheduleService.createSchedule(request));
    }

    // 일정 전체 조회
    @GetMapping
    public ResponseEntity<List<GetScheduleResponse>> getAllSchedule() {
        return ResponseEntity.ok(scheduleService.getAllSchedule());
    }

    // 일정 단 건 조회
    @GetMapping("/{scheduleId}")
    public ResponseEntity<GetScheduleResponse> getOneSchedule(@PathVariable Long scheduleId) {
        return ResponseEntity.ok(scheduleService.getOneSchedule(scheduleId));
    }

    // 일정 수정
    @PutMapping("/{scheduleId}")
    public ResponseEntity<UpdateScheduleResponse> updateSchedule(@PathVariable Long scheduleId, @Valid @RequestBody UpdateScheduleRequest request) {
        return ResponseEntity.ok(scheduleService.updateSchedule(scheduleId, request));
    }

    // 일정 삭제
    @DeleteMapping("/{userId}/{scheduleId}")
    public ResponseEntity<Void> DeleteSchedule(@PathVariable Long userId, @PathVariable Long scheduleId) {
        scheduleService.deleteSchedule(userId, scheduleId);
        return ResponseEntity.noContent().build();
    }
}
