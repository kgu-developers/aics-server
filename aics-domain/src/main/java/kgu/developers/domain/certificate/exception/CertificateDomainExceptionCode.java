package kgu.developers.domain.certificate.exception;

import kgu.developers.common.exception.ExceptionCode;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

import static org.springframework.http.HttpStatus.BAD_REQUEST;
import static org.springframework.http.HttpStatus.NOT_FOUND;

@Getter
@AllArgsConstructor
public enum CertificateDomainExceptionCode implements ExceptionCode {
    CERTIFICATE_NOT_FOUND(NOT_FOUND, "해당 자격증을 찾을 수 없습니다."),
    CERTIFICATE_NOT_IN_SUBMISSION_PERIOD_EXCEPTION(BAD_REQUEST, "현재 자격증 제출 기간이 아닙니다."),
    CERTIFICATE_INVALID_GRADUATION_TYPE_EXCEPTION(BAD_REQUEST, "선택하신 제출 방식이 자격증이 아닙니다."),
    ;

    private final HttpStatus status;
    private final String message;

    @Override
    public String getCode() {
        return this.name();
    }
}
