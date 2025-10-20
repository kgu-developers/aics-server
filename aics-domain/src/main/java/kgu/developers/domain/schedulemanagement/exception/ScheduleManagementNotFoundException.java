package kgu.developers.domain.schedulemanagement.exception;

import kgu.developers.common.exception.CustomException;

import static kgu.developers.domain.schedulemanagement.exception.ScheduleManagementDomainExceptionCode.SCHEDULE_MANAGEMENT_NOT_FOUND;

public class ScheduleManagementNotFoundException extends CustomException {
    public ScheduleManagementNotFoundException() {super(SCHEDULE_MANAGEMENT_NOT_FOUND);}
}
