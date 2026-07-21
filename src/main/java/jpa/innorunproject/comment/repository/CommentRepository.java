package jpa.innorunproject.comment.repository;

import jpa.innorunproject.comment.domain.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment, Long> {
    List<Comment> findAllByScheduleId(Long scheduleId);

    int countByScheduleId(Long id);
}
