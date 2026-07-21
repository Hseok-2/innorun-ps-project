package jpa.innorunproject.comment.service;

import jpa.innorunproject.comment.domain.Comment;
import jpa.innorunproject.comment.dto.CreateCommentRequest;
import jpa.innorunproject.comment.dto.CreateCommentResponse;
import jpa.innorunproject.comment.dto.GetCommentResponse;
import jpa.innorunproject.comment.repository.CommentRepository;
import jpa.innorunproject.schedule.domain.Schedule;
import jpa.innorunproject.schedule.exception.ScheduleNotFoundException;
import jpa.innorunproject.schedule.repository.ScheduleRepository;
import jpa.innorunproject.user.domain.User;
import jpa.innorunproject.user.exception.UserNotFoundException;
import jpa.innorunproject.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class CommentService {

    private final CommentRepository commentRepository;
    private final UserRepository userRepository;
    private final ScheduleRepository scheduleRepository;

    // 댓글 등록
    public CreateCommentResponse createComment(Long scheduleId, CreateCommentRequest request) {
        // 유저 존재 여부
        User user = userRepository.findById(request.getUserId())
                .orElseThrow(() -> new UserNotFoundException("해당 유저는 존재하지 않습니다."));

        // 일정 존재 여부
        Schedule schedule = scheduleRepository.findById(scheduleId)
                .orElseThrow(() -> new ScheduleNotFoundException("해당 일정은 존재하지 않습니다."));

        Comment savedComment = commentRepository.save(request.toEntity(user, schedule));
        return CreateCommentResponse.from(savedComment);
    }

    // 댓글 전체 조회
    @Transactional(readOnly = true)
    public List<GetCommentResponse> getAll(Long scheduleId) {

        Schedule schedule = scheduleRepository.findById(scheduleId)
                .orElseThrow(() -> new ScheduleNotFoundException("해당 일정은 존재하지 않습니다."));

        return commentRepository.findAllByScheduleId(schedule.getId()).stream()
                .map(GetCommentResponse::from)
                .toList();
    }
}
