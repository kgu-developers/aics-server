package kgu.developers.domain.user.infrastructure;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import kgu.developers.common.domain.BaseRole;
import kgu.developers.common.domain.BaseTimeEntity;
import kgu.developers.domain.user.domain.Major;
import kgu.developers.domain.user.domain.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import static jakarta.persistence.EnumType.STRING;
import static lombok.AccessLevel.PROTECTED;

@Entity
@Getter
@Builder
@Table(name = "\"user\"")
@NoArgsConstructor(access = PROTECTED)
@AllArgsConstructor(access = PROTECTED)
public class UserJpaEntity extends BaseTimeEntity {

    @Id
    @Column(length = 10)
    private String id;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false, length = 10)
    private String name;

    @Column(unique = true, nullable = false, length = 50)
    private String email;

    @Column(unique = true, nullable = false, length = 15)
    private String phone;

    @Column(nullable = false)
    @Enumerated(STRING)
    private BaseRole role;

    @Column(nullable = false)
    @Enumerated(STRING)
    private Major major;

    public static UserJpaEntity toEntity(final User user) {

        if ( user == null ) {
            return null;
        }

        UserJpaEntity entity = UserJpaEntity.builder()
            .id(user.getId())
            .password(user.getPassword())
            .name(user.getName())
            .email(user.getEmail())
            .phone(user.getPhone())
            .role(user.getRole())
            .major(user.getMajor())
            .build();

        entity.setDeletedAt(user.getDeletedAt());

        return entity;
    }

    public static User toDomain(UserJpaEntity entity) {

        if ( entity == null ) {
            return null;
        }

        return User.builder()
            .id(entity.getId())
            .password(entity.getPassword())
            .name(entity.getName())
            .email(entity.getEmail())
            .phone(entity.getPhone())
            .role(entity.getRole())
            .major(entity.getMajor())
            .createdAt(entity.getCreatedAt())
            .updatedAt(entity.getUpdatedAt())
            .deletedAt(entity.getDeletedAt())
            .build();
    }

}