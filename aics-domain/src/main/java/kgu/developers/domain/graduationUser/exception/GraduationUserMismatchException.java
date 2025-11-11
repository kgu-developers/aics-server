package kgu.developers.domain.graduationUser.exception;

import kgu.developers.common.exception.CustomException;

import static kgu.developers.domain.graduationUser.exception.GraduationUserDomainExceptionCode.GRADUATION_USER_MISMATCH;

public class GraduationUserMismatchException extends CustomException {
    public GraduationUserMismatchException() {
        super(GRADUATION_USER_MISMATCH);
    }
}
