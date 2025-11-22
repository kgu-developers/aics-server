package kgu.developers.domain.graduationUser.exception;

import kgu.developers.common.exception.CustomException;

import static kgu.developers.domain.graduationUser.exception.GraduationUserDomainExceptionCode.GRADUATION_USER_ID_DUPLICATED;

public class GraduationUserIdDuplicateException extends CustomException {
    public GraduationUserIdDuplicateException() {
        super(GRADUATION_USER_ID_DUPLICATED);
    }
}
