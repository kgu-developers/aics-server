package kgu.developers.domain.thesis.exception;

import kgu.developers.common.exception.CustomException;

import static kgu.developers.domain.thesis.exception.ThesisDomainExceptionCode.THESIS_NOT_FOUND;

public class ThesisNotFoundException extends CustomException {
    public ThesisNotFoundException() {
        super(THESIS_NOT_FOUND);
    }
}
