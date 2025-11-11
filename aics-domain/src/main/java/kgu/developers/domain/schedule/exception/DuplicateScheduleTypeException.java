package kgu.developers.domain.schedule.exception;

import kgu.developers.common.exception.CustomException;

import static kgu.developers.domain.schedule.exception.ScheduleDomainExceptionCode.DUPLICATE_SCHEDULE_TYPE;

public class DuplicateScheduleTypeException extends CustomException {
    public DuplicateScheduleTypeException() {super(DUPLICATE_SCHEDULE_TYPE);}
}
