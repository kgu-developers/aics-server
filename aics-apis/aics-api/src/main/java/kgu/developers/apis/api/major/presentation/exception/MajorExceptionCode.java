package kgu.developers.apis.api.major.presentation.exception;

import kgu.developers.core.common.exception.ExceptionCode;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum MajorExceptionCode implements ExceptionCode {
    MAJOR_NOT_FOUND(HttpStatus.NOT_FOUND, "확인되지 않는 전공입니다."),
    MAJOR_DUPLICATED(HttpStatus.CONFLICT, "이미 존재하는 전공입니다."),
    ;

    private final HttpStatus status;
    private final String message;


    @Override
    public String getCode() {
        return this.name();
    }
}
