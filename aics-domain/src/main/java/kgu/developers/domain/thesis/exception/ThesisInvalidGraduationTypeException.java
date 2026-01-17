package kgu.developers.domain.thesis.exception;

import kgu.developers.common.exception.CustomException;

import static kgu.developers.domain.thesis.exception.ThesisDomainExceptionCode.THESIS_SUBMISSION_TYPE_MISMATCH;

public class ThesisInvalidGraduationTypeException extends CustomException {
    public ThesisInvalidGraduationTypeException() {
        super(THESIS_SUBMISSION_TYPE_MISMATCH);
    }
}
