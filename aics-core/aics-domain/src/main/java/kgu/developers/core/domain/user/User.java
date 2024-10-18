package kgu.developers.core.domain.user;

import jakarta.persistence.*;
import kgu.developers.core.common.domain.BaseTimeEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import static jakarta.persistence.FetchType.LAZY;
import static jakarta.persistence.GenerationType.IDENTITY;
import static lombok.AccessLevel.PROTECTED;

@Entity
@Getter
@Table(name = "users")
@NoArgsConstructor(access = PROTECTED)
@AllArgsConstructor
public class User extends BaseTimeEntity {
    @Id
    @Column(name = "user_id")
    @GeneratedValue(strategy = IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false, updatable = false, length = 10)
    private String personalId;

    @Column(nullable = false, length = 20)
    private String password;

    @Column(nullable = false, length = 20)
    private String name;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private Gender gender;

    @Column(nullable = false, length = 8)
    private String birth;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private Grade grade;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private Status status;

    @Column(name = "user_role", nullable = false)
    @Enumerated(EnumType.STRING)
    private Role role;

    @Column(nullable = false)
    private boolean hasAiAccess;

    /*
    // 전공 테이블 연결
    @Column(nullable = false)
    @ManyToOne(fetch = LAZY)
    private Major majorId;
     */

    private User(String personalId, String password, String name, Gender gender, String birth, Grade grade, Status status, Role role) {
        this.personalId = personalId;
        this.password = password;
        this.name = name;
        this.gender = gender;
        this.birth = birth;
        this.status = status;
        this.grade = grade;
        this.role = role;
    }

    public static User of(String personalId, String password, String name, Gender gender, String birth, Grade grade, Status status, Role role){
        return new User(personalId, password, name, gender, birth, grade, status, role);
    }
}
