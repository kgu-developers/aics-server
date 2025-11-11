package kgu.developers.domain.graduationUser.infrastructure.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import kgu.developers.common.domain.BaseTimeEntity;
import kgu.developers.domain.graduationUser.domain.GraduationType;
import kgu.developers.domain.graduationUser.domain.GraduationUser;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

import static jakarta.persistence.EnumType.STRING;
import static lombok.AccessLevel.PRIVATE;
import static lombok.AccessLevel.PROTECTED;

@Entity
@Getter
@Builder
@Table(name = "\"graduation_user\"")
@NoArgsConstructor(access = PROTECTED)
@AllArgsConstructor(access = PRIVATE)
public class GraduationUserJpaEntity extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 10)
    private String name;

    @Column(unique = true, nullable = false, length = 50)
    private String email;

    @Column(nullable = false, length = 20)
    private String advisorProfessor;

    @Column(nullable = false)
    @Enumerated(STRING)
    private GraduationType graduationType;

    private LocalDate graduationDate;

    private Long midThesisId;

    private Long finalThesisId;

    private Long certificateId;

    private String userId;

    public static GraduationUserJpaEntity toEntity(GraduationUser graduationUser) {
        if(graduationUser == null) {
            return null;
        }

        return GraduationUserJpaEntity.builder()
            .id(graduationUser.getId())
            .name(graduationUser.getName())
            .email(graduationUser.getEmail())
            .advisorProfessor(graduationUser.getAdvisorProfessor())
            .graduationType(graduationUser.getGraduationType())
            .graduationDate(graduationUser.getGraduationDate())
            .midThesisId(graduationUser.getMidThesisId())
            .finalThesisId(graduationUser.getFinalThesisId())
            .certificateId(graduationUser.getCertificateId())
            .userId(graduationUser.getUserId())
            .build();
    }

    public static GraduationUser toDomain(GraduationUserJpaEntity entity) {
        if(entity == null) {
            return null;
        }

        return GraduationUser.builder()
            .id(entity.getId())
            .name(entity.getName())
            .email(entity.getEmail())
            .advisorProfessor(entity.getAdvisorProfessor())
            .graduationType(entity.getGraduationType())
            .graduationDate(entity.getGraduationDate())
            .midThesisId(entity.getMidThesisId())
            .finalThesisId(entity.getFinalThesisId())
            .certificateId(entity.getCertificateId())
            .userId(entity.getUserId())
            .createdAt(entity.getCreatedAt())
            .updatedAt(entity.getUpdatedAt())
            .deletedAt(entity.getDeletedAt())
            .build();
    }

}