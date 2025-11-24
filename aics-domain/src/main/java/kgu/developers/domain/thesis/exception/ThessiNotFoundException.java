package kgu.developers.domain.thesis.exception;

import kgu.developers.common.exception.CustomException;

import static kgu.developers.domain.thesis.exception.ThesisDomainExceptionCode.THESIS_NOT_FOUND;

public class ThessiNotFoundException extends CustomException {
    public ThessiNotFoundException() {
        super(THESIS_NOT_FOUND);
    }
}
