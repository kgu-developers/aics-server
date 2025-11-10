package kgu.developers.domain.graduationUser.exception;

import kgu.developers.common.exception.CustomException;

import static kgu.developers.domain.graduationUser.exception.GraduationUserDomainExceptionCode.GRADUATION_USER_NOT_FOUND;

public class GraduationUserNotFoundException extends CustomException {
    public GraduationUserNotFoundException() {
        super(GRADUATION_USER_NOT_FOUND);
    }
}
