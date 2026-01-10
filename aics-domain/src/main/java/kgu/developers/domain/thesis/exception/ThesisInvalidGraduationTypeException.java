package kgu.developers.domain.thesis.exception;

import kgu.developers.common.exception.CustomException;

import static kgu.developers.domain.thesis.exception.ThesisDomainExceptionCode.THESIS_INVALID_GRADUATION_TYPE_EXCEPTION;

public class ThesisInvalidGraduationTypeException extends CustomException {
    public ThesisInvalidGraduationTypeException() {
        super(THESIS_INVALID_GRADUATION_TYPE_EXCEPTION);
    }
}
