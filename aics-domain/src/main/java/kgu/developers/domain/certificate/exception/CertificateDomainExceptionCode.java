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
    CERTIFICATE_NOT_IN_SUBMISSION_PERIOD(BAD_REQUEST, "자격증 제출 기간이 아닙니다."),
    CERTIFICATE_SUBMISSION_TYPE_MISMATCH(BAD_REQUEST, "이전에 선택한 제출 방식과 자격증 제출이 일치하지 않습니다."),
    ;

    private final HttpStatus status;
    private final String message;

    @Override
    public String getCode() {
        return this.name();
    }
}
