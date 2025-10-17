package kgu.developers.domain.schedulemanagement.infrastructure;

import jakarta.persistence.*;
import kgu.developers.domain.schedulemanagement.domain.ScheduleManagement;
import kgu.developers.domain.schedulemanagement.domain.SubmissionType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

import static jakarta.persistence.GenerationType.IDENTITY;

@Entity
@Getter
@Builder
@Table(name = "schedulemanagement")
@NoArgsConstructor
@AllArgsConstructor
public class ScheduleManagementJpaEntity {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long id;

    @Column(nullable = false)
    private SubmissionType submissionType;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private LocalDateTime startDate;

    @Column(nullable = false)
    private LocalDateTime endDate;

    public static ScheduleManagementJpaEntity toEntity(final ScheduleManagement scheduleManagement) {
        if (scheduleManagement == null) {
            return null;
        }

        return ScheduleManagementJpaEntity.builder()
                .id(scheduleManagement.getId())
                .submissionType(scheduleManagement.getSubmissionType())
                .title(scheduleManagement.getTitle())
                .startDate(scheduleManagement.getStartDate())
                .endDate(scheduleManagement.getEndDate())
                .build();
    }
    public static ScheduleManagement toDomain(ScheduleManagementJpaEntity entity){
        if (entity == null) {
            return null;
        }
        return ScheduleManagement.builder()
                .id(entity.getId())
                .submissionType(entity.getSubmissionType())
                .title(entity.getTitle())
                .startDate(entity.getStartDate())
                .endDate(entity.getEndDate())
                .build();
    }
}