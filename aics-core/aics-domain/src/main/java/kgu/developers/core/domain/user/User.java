package kgu.developers.core.domain.user;

import jakarta.persistence.*;
import jakarta.persistence.criteria.MapJoin;
import kgu.developers.core.common.domain.BaseTimeEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@Table(name = "users")
@NoArgsConstructor
@AllArgsConstructor
public class User extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "personal_id", unique = true, nullable = false, updatable = false, length = 10)
    private String personalId;

    @Column(name = "password", nullable = false, length = 20)
    private String password;

    @Column(name = "name", nullable = false, length = 20)
    private String name;

    @Column(name = "gender", nullable = false)
    @Enumerated(EnumType.STRING)
    private Gender gender;

    @Column(name = "birth", nullable = false, length = 8)
    private String birth;

    @Column(name = "grade", nullable = false)
    @Enumerated(EnumType.STRING)
    private Grade grade;

    @Column(name = "status", nullable = false)
    @Enumerated(EnumType.STRING)
    private Status status;

    @Column(name = "user_role", nullable = false)
    @Enumerated(EnumType.STRING)
    private Role role;

    @Column(name = "has_ai_access", nullable = false)
    private boolean hasAiAccess;

    /*
    // 전공 테이블 연결
    @Column(name = "major_id", nullable = false)
    @ManyToOne(fetch = FetchType.LAZY)
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
