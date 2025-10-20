package kgu.developers.domain.schedulemanagement.exception;

import kgu.developers.common.exception.ExceptionCode;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

import static org.springframework.http.HttpStatus.NOT_FOUND;

@Getter
@AllArgsConstructor
public enum ScheduleManagementDomainExceptionCode implements ExceptionCode {
    SCHEDULE_MANAGEMENT_NOT_FOUND(NOT_FOUND,"해당 일정을 찾을 수 없습니다.");

    private final HttpStatus status;
    private final String message;

    @Override
    public String getCode() {return this.name();}


}
