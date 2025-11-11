package kgu.developers.domain.schedule.exception;

import kgu.developers.common.exception.ExceptionCode;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

import static org.springframework.http.HttpStatus.BAD_REQUEST;
import static org.springframework.http.HttpStatus.NOT_FOUND;

@Getter
@AllArgsConstructor
public enum ScheduleDomainExceptionCode implements ExceptionCode {
    DUPLICATE_SCHEDULE_TYPE(BAD_REQUEST, "동일 제출 유형의 일정이 이미 존재합 니다."),
    SCHEDULE_MANAGEMENT_NOT_FOUND(NOT_FOUND,"해당 일정을 찾을 수 없습니다.");

    private final HttpStatus status;
    private final String message;

    @Override
    public String getCode() {return this.name();}


}
