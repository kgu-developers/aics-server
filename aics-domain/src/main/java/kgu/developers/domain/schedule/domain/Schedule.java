package kgu.developers.domain.schedule.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Schedule {

    private Long id;
    private SubmissionType submissionType;
    private String content;
    private LocalDateTime startDate;
    private LocalDateTime endDate;

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private LocalDateTime deletedAt;


    public static Schedule create(SubmissionType submissionType,String content, LocalDateTime startDate, LocalDateTime endDate) {
        return Schedule.builder()
                .submissionType(submissionType)
                .content(content)
                .startDate(startDate)
                .endDate(endDate)
                .build();
    }
    public ScheduleStatus determineStatusAt(LocalDateTime referenceTime) {
        if (referenceTime.isBefore(startDate)) {
            return ScheduleStatus.PENDING;
        }
        if (referenceTime.isAfter(endDate)) {
            return ScheduleStatus.CLOSED;
        }
        return ScheduleStatus.IN_PROGRESS;
    }

    public boolean isInProgress(LocalDateTime referenceTime) {
        return determineStatusAt(referenceTime) == ScheduleStatus.IN_PROGRESS;
    }

    public void updateSubmissionType(SubmissionType submissionType) {
        this.submissionType = submissionType;
    }
    public void updateContent(String content) {this.content = content;}
    public void updateStartDate(LocalDateTime startDate) {
        this.startDate = startDate;
    }
    public void updateEndDate(LocalDateTime endDate) {
        this.endDate = endDate;
    }

}
