package kgu.developers.domain.graduationUser.exception;

import kgu.developers.common.exception.ExceptionCode;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

import static org.springframework.http.HttpStatus.CONFLICT;
import static org.springframework.http.HttpStatus.FORBIDDEN;
import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;
import static org.springframework.http.HttpStatus.NOT_FOUND;

@Getter
@AllArgsConstructor
public enum GraduationUserDomainExceptionCode implements ExceptionCode {
    GRADUATION_USER_NOT_FOUND(NOT_FOUND, "해당 졸업 대상자를 찾을 수 없습니다."),
    GRADUATION_USER_MISMATCH(FORBIDDEN, "해당 졸업 대상자로의 접근 권한이 없습니다."),
    GRADUATION_USER_ID_DUPLICATED(CONFLICT, "이미 동일한 학번의 졸업 대상자 정보가 존재합니다."),
    GRADUATION_USER_EXCEL_GENERATION_FAILED(INTERNAL_SERVER_ERROR, "졸업 대상자 엑셀 파일의 생성 중 오류가 발생했습니다.")
    ;

    private final HttpStatus status;
    private final String message;

    @Override
    public String getCode() {
        return this.name();
    }

}
