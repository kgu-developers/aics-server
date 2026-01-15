package kgu.developers.domain.thesis.exception;

import kgu.developers.common.exception.CustomException;

import static kgu.developers.domain.thesis.exception.ThesisDomainExceptionCode.THESIS_INVALID_GRADUATION_TYPE_EXCEPTION;

public class ThesisInvalidSubmissionTypeException extends CustomException {
    public ThesisInvalidSubmissionTypeException() {
        super(THESIS_INVALID_GRADUATION_TYPE_EXCEPTION);
    }
}
