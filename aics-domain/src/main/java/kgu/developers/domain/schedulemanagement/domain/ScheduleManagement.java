package kgu.developers.domain.schedulemanagement.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ScheduleManagement {

    private long id;
    private SubmissionType submissionType;
    private String title;
    private LocalDateTime startDate;
    private LocalDateTime endDate;

    public static ScheduleManagement create(SubmissionType submissionType, String title, LocalDateTime startDate, LocalDateTime endDate) {
        return ScheduleManagement.builder()
                .submissionType(submissionType)
                .title(title)
                .startDate(startDate)
                .endDate(endDate)
                .build();
    }
    public void updateSubmissionType(SubmissionType submissionType) {
        this.submissionType = submissionType;
    }
    public void updateTitle(String title) {
        this.title = title;
    }
    public void updateStartDate(LocalDateTime startDate) {
        this.startDate = startDate;
    }
    public void updateEndDate(LocalDateTime endDate) {
        this.endDate = endDate;
    }

}
