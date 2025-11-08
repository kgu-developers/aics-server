package kgu.developers.domain.schedule.infrastructure.entity;

import jakarta.persistence.*;
import kgu.developers.common.domain.BaseTimeEntity;
import kgu.developers.domain.schedule.domain.Schedule;
import kgu.developers.domain.schedule.domain.SubmissionType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

import static jakarta.persistence.EnumType.STRING;
import static jakarta.persistence.GenerationType.IDENTITY;
import static lombok.AccessLevel.PROTECTED;

@Entity
@Getter
@Builder
@Table(name = "schedule", uniqueConstraints = @UniqueConstraint(columnNames = "submission_type"))
@NoArgsConstructor
@AllArgsConstructor(access = PROTECTED)
public class ScheduleJpaEntity extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long id;

    @Column(name = "submission_type",nullable = false)
    @Enumerated(STRING)
    private SubmissionType submissionType;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false, columnDefinition = "text")
    private String content;

    @Column(nullable = false)
    private LocalDateTime startDate;

    @Column(nullable = false)
    private LocalDateTime endDate;

    public static ScheduleJpaEntity toEntity(final Schedule schedule) {
        if (schedule == null) {
            return null;
        }

        return ScheduleJpaEntity.builder()
                .id(schedule.getId())
                .submissionType(schedule.getSubmissionType())
                .title(schedule.getTitle())
                .content(schedule.getContent())
                .startDate(schedule.getStartDate())
                .endDate(schedule.getEndDate())
                .build();
    }
    public Schedule toDomain(){
        return Schedule.builder()
                .id(id)
                .submissionType(submissionType)
                .title(title)
                .startDate(startDate)
                .content(content)
                .endDate(endDate)
                .createdAt(getCreatedAt())
                .updatedAt(getUpdatedAt())
                .deletedAt(getDeletedAt())
                .build();
    }
}