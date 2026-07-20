package jpa.innorunproject.schedule.repository;

import jpa.innorunproject.schedule.domain.Schedule;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ScheduleRepository extends JpaRepository<Schedule, Long> {
}
