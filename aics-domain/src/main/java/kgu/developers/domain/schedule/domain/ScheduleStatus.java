package kgu.developers.domain.schedule.domain;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum ScheduleStatus {
    PENDING("대기"),
    IN_PROGRESS("진행 중"),
    CLOSED("마감")
    ;
    private final String label;
}
