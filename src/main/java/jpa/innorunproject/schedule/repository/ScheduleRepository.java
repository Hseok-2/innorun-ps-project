package jpa.innorunproject.schedule.repository;

import jpa.innorunproject.schedule.domain.Schedule;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ScheduleRepository extends JpaRepository<Schedule, Long> {
    List<Schedule> findAllByUserId(Long userId);
}
