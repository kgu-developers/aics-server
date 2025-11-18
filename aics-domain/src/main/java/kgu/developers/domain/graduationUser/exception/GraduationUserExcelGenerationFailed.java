package kgu.developers.domain.graduationUser.exception;

import kgu.developers.common.exception.CustomException;

import static kgu.developers.domain.graduationUser.exception.GraduationUserDomainExceptionCode.GRADUATION_USER_EXCEL_GENERATION_FAILED;

public class GraduationUserExcelGenerationFailed extends CustomException {
    public GraduationUserExcelGenerationFailed() {
        super(GRADUATION_USER_EXCEL_GENERATION_FAILED);
    }

    public GraduationUserExcelGenerationFailed(Throwable cause) {
        super(GRADUATION_USER_EXCEL_GENERATION_FAILED, cause);
    }
}
