package jpa.innorunproject.comment.domain;

import jakarta.persistence.*;
import jpa.innorunproject.global.domain.BaseEntity;
import jpa.innorunproject.schedule.domain.Schedule;
import jpa.innorunproject.user.domain.User;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "comment")
public class Comment extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String content;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "schedule_id", nullable = false)
    private Schedule schedule;

    @Builder
    private Comment(String content, User user, Schedule schedule) {
        this.content = content;
        this.user = user;
        this.schedule = schedule;
    }

}
