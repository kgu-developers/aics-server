package kgu.developers.domain.schedule.exception;

import kgu.developers.common.exception.CustomException;

import static kgu.developers.domain.schedule.exception.ScheduleDomainExceptionCode.SCHEDULE_MANAGEMENT_NOT_FOUND;

public class ScheduleNotFoundException extends CustomException {
    public ScheduleNotFoundException() {super(SCHEDULE_MANAGEMENT_NOT_FOUND);}
}
