package jpa.innorunproject.user.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import jpa.innorunproject.global.domain.BaseEntity;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "users")
public class User extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(length = 20, nullable = false)
    private String username;
    @Column(unique = true, nullable = false)
    private String email;
    @Column(nullable = false)
    @Size(min = 8, message = "비밀번호는 {min}자 이상이어야 합니다.")
    private String password;

    @Builder
    private User(String username, String email, String password) {
        this.username = username;
        this.email = email;
        this.password = password;
    }

    public void updateInfo(String username, String email) {
        this.username = username;
        this.email = email;
    }

    public void updatePassword(String password) {
        this.password = password;
    }
}
