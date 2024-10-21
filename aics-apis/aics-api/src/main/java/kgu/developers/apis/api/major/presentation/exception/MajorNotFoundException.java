package kgu.developers.apis.api.major.presentation.exception;

import kgu.developers.core.common.exception.CustomException;

import static kgu.developers.apis.api.major.presentation.exception.MajorExceptionCode.MAJOR_NOT_FOUND;

public class MajorNotFoundException extends CustomException {
    public MajorNotFoundException() {
        super(MAJOR_NOT_FOUND);
    }
}
