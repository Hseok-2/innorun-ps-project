package jpa.innorunproject.schedule.service;

import jpa.innorunproject.comment.repository.CommentRepository;
import jpa.innorunproject.schedule.domain.Schedule;
import jpa.innorunproject.schedule.dto.*;
import jpa.innorunproject.schedule.exception.ScheduleNotFoundException;
import jpa.innorunproject.schedule.exception.ScheduleUnAccessException;
import jpa.innorunproject.schedule.repository.ScheduleRepository;
import jpa.innorunproject.user.domain.User;
import jpa.innorunproject.user.exception.UserNotFoundException;
import jpa.innorunproject.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Objects;

@Service
@Transactional
@RequiredArgsConstructor
public class ScheduleService {

    private final ScheduleRepository scheduleRepository;
    private final UserRepository userRepository;
    private final CommentRepository commentRepository;

    // 등록
    public CreateScheduleResponse createSchedule(CreateScheduleRequest request) {
        // 해당 User가 없다면 예외 처리
        User user = userRepository.findById(request.getUserId())
                .orElseThrow(() -> new UserNotFoundException("해당 유저는 존재하지 않습니다."));

        return CreateScheduleResponse.from(scheduleRepository.save(request.toEntity(user)));
    }

    // 전체 조회
    @Transactional(readOnly = true)
    public Page<GetSchedulePageResponse> getAllSchedule(int page, int size) {

        Pageable pageable = PageRequest.of(page, size, Sort.by("updatedAt").descending());

        Page<Schedule> schedulePage = scheduleRepository.findAll(pageable);

        return schedulePage.map(schedule -> {
            int commentCnt = commentRepository.countByScheduleId(schedule.getId());
            return GetSchedulePageResponse.from(schedule, commentCnt);
        });
    }

    // 단 건 조회
    @Transactional(readOnly = true)
    public GetScheduleResponse getOneSchedule(Long scheduleId) {
        return scheduleRepository.findById(scheduleId)
                .map(GetScheduleResponse::from)
                .orElseThrow(() -> new ScheduleNotFoundException("해당 일정은 존재하지 않습니다."));
    }

    // 해당 유저의 일정 전체 조회
    @Transactional(readOnly = true)
    public Page<GetSchedulePageResponse> getAllByUserId(Long userId, int page, int size) {
        if (!userRepository.existsById(userId)) {
            throw new UserNotFoundException("해당 유저는 존재하지 않습니다.");
        }

        Pageable pageable = PageRequest.of(page, size, Sort.by("updatedAt").descending());

        Page<Schedule> schedulePage = scheduleRepository.findAllByUserId(userId, pageable);

        return schedulePage.map(schedule -> {
            int commentCnt = commentRepository.countByScheduleId(schedule.getId());
            return GetSchedulePageResponse.from(schedule, commentCnt);
        });
    }

    // 수정
    public UpdateScheduleResponse updateSchedule(Long scheduleId, UpdateScheduleRequest request) {
        Schedule schedule = scheduleRepository.findById(scheduleId)
                .orElseThrow(() -> new ScheduleNotFoundException("해당 일정은 존재하지 않습니다."));

        // 해당 유저가 해당 일정의 주인인지 여부, npe 방지
        if (schedule.getUser() == null || !Objects.equals(schedule.getUser().getId(), request.getUserId())) {
            throw new ScheduleUnAccessException("해당 유저의 일정이 존재하지 않거나 권한이 없습니다.");
        }

        schedule.update(request.getTitle(), request.getContent());
        scheduleRepository.saveAndFlush(schedule);

        return UpdateScheduleResponse.from(schedule);
    }


    // 삭제
    public void deleteSchedule(Long scheduleId, Long userId) {
        Schedule schedule = scheduleRepository.findById(scheduleId)
                .orElseThrow(() -> new ScheduleNotFoundException("해당 일정은 존재하지 않습니다."));

        // 해당 유저가 해당 일정의 주인인지 여부, npe 방지
        if (schedule.getUser() == null || !Objects.equals(schedule.getUser().getId(),userId)) {
            throw new ScheduleUnAccessException("해당 유저의 일정이 존재하지 않거나 권한이 없습니다.");
        }

        scheduleRepository.delete(schedule);
    }

}
