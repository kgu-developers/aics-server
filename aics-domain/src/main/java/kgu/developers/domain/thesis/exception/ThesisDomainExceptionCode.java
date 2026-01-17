package kgu.developers.domain.thesis.exception;

import kgu.developers.common.exception.ExceptionCode;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

import static org.springframework.http.HttpStatus.BAD_REQUEST;

@Getter
@AllArgsConstructor
public enum ThesisDomainExceptionCode implements ExceptionCode {
    THESIS_NOT_FOUND(BAD_REQUEST, "해당 논문을 찾을 수 없습니다."),
    THESIS_NOT_IN_SUBMISSION_PERIOD(BAD_REQUEST, "현재 논문 제출 기간이 아닙니다."),
    THESIS_SUBMISSION_TYPE_MISMATCH(BAD_REQUEST, "이전에 선택한 제출 방식과 논문 제출이 일치하지 않습니다."),
    THESIS_INVALID_SUBMISSION_TYPE(BAD_REQUEST, "요청하신 제출 유형이 논문이 아닙니다."),
    ;

    private final HttpStatus status;
    private final String message;

    @Override
    public String getCode() {
        return this.name();
    }
}
