package jpa.innorunproject.schedule.service;

import jakarta.validation.Valid;
import jpa.innorunproject.schedule.domain.Schedule;
import jpa.innorunproject.schedule.dto.*;
import jpa.innorunproject.schedule.exception.ScheduleNotFoundException;
import jpa.innorunproject.schedule.repository.ScheduleRepository;
import lombok.RequiredArgsConstructor;
import org.jspecify.annotations.Nullable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class ScheduleService {

    private final ScheduleRepository scheduleRepository;

    // 등록
    public CreateScheduleResponse createSchedule(CreateScheduleRequest request) {
        return CreateScheduleResponse.from(scheduleRepository.save(request.toEntity()));
    }

    // 전체 조회
    @Transactional(readOnly = true)
    public List<GetScheduleResponse> getAllSchedule() {
        return scheduleRepository.findAll().stream()
                .map(GetScheduleResponse::from)
                .toList();
    }

    // 단 건 조회
    @Transactional(readOnly = true)
    public GetScheduleResponse getOneSchedule(Long scheduleId) {
        return scheduleRepository.findById(scheduleId)
                .map(GetScheduleResponse::from)
                .orElseThrow(() -> new ScheduleNotFoundException("해당 일정은 존재하지 않습니다."));
    }

    // 수정
    public UpdateScheduleResponse updateSchedule(Long scheduleId, UpdateScheduleRequest request) {
        Schedule schedule = scheduleRepository.findById(scheduleId)
                .orElseThrow(() -> new ScheduleNotFoundException("해당 일정은 존재하지 않습니다."));

        schedule.update(request.getTitle(), request.getContent());
        scheduleRepository.saveAndFlush(schedule);

        return UpdateScheduleResponse.from(schedule);
    }

    // 삭제
    public void deleteSchedule(Long scheduleId) {
        Schedule schedule = scheduleRepository.findById(scheduleId)
                .orElseThrow(() -> new ScheduleNotFoundException("해당 일정은 존재하지 않습니다."));
        scheduleRepository.delete(schedule);
    }
}
