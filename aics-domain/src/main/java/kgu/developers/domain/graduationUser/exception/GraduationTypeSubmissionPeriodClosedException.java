package kgu.developers.domain.graduationUser.exception;

import kgu.developers.common.exception.CustomException;

import static kgu.developers.domain.graduationUser.exception.GraduationUserDomainExceptionCode.GRADUATION_TYPE_SUBMISSION_PERIOD_CLOSED;

public class GraduationTypeSubmissionPeriodClosedException extends CustomException {
    public GraduationTypeSubmissionPeriodClosedException() {
        super(GRADUATION_TYPE_SUBMISSION_PERIOD_CLOSED);
    }
}
