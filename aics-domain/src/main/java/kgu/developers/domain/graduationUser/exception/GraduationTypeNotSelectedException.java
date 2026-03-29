package kgu.developers.domain.graduationUser.exception;

import kgu.developers.common.exception.CustomException;

import static kgu.developers.domain.graduationUser.exception.GraduationUserDomainExceptionCode.GRADUATION_TYPE_NOT_SELECTED;

public class GraduationTypeNotSelectedException extends CustomException {
    public GraduationTypeNotSelectedException() {
        super(GRADUATION_TYPE_NOT_SELECTED);
    }
}
