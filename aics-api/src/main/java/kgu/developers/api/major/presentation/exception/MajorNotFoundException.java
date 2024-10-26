package kgu.developers.api.major.presentation.exception;

import kgu.developers.common.exception.CustomException;

import static kgu.developers.api.major.presentation.exception.MajorExceptionCode.MAJOR_NOT_FOUND;

public class MajorNotFoundException extends CustomException {
    public MajorNotFoundException() {
        super(MAJOR_NOT_FOUND);
    }
}
