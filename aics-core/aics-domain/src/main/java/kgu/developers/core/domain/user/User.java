package kgu.developers.core.domain.user;

import static jakarta.persistence.CascadeType.ALL;
import static jakarta.persistence.EnumType.STRING;
import static jakarta.persistence.FetchType.LAZY;
import static jakarta.persistence.GenerationType.IDENTITY;
import static kgu.developers.core.domain.user.Role.GUEST;
import static kgu.developers.core.domain.user.Status.INSCHOOL;
import static lombok.AccessLevel.PROTECTED;

import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import kgu.developers.core.common.domain.BaseTimeEntity;
import kgu.developers.core.domain.major.Major;
import kgu.developers.core.domain.post.Post;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Builder
@Table(name = "\"user\"")
@AllArgsConstructor
@NoArgsConstructor(access = PROTECTED)
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

    @Column(nullable = false, length = 8)
    private String birth;

    @Column(nullable = false)
    @Enumerated(STRING)
    private Gender gender;

    @Column(nullable = false)
    @Enumerated(STRING)
    private Grade grade;

    @Column(nullable = false)
    @Enumerated(STRING)
    private Status status;

    @Column(nullable = false)
    @Enumerated(STRING)
    private Role role;

    @Column(nullable = false)
    private boolean hasAiAccess;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "major_id")
    private Major major;

    @Builder.Default
    @OneToMany(mappedBy = "author", cascade = ALL, fetch = LAZY, orphanRemoval = true)
    List<Post> posts = new ArrayList<>();

    public static User create(String personalId, String password, String name, String birth, Gender gender, Grade grade) {
        return User.builder()
            .personalId(personalId)
            .password(password)
            .name(name)
            .birth(birth)
            .gender(gender)
            .grade(grade)
            .status(INSCHOOL)
            .role(GUEST)
            .hasAiAccess(false)
            .build();
    }
}
