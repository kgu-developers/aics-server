package kgu.developers.domain.graduationUser.exception;

import kgu.developers.common.exception.CustomException;

import static kgu.developers.domain.graduationUser.exception.GraduationUserDomainExceptionCode.GRADUATION_USER_SUBMISSION_MISMATCH;

public class GraudationUserSubmissionMismatchException extends CustomException {
    public GraudationUserSubmissionMismatchException() {
        super(GRADUATION_USER_SUBMISSION_MISMATCH);
    }
}
