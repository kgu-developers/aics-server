package kgu.developers.apis.api.major.presentation.exception;

import kgu.developers.core.common.exception.CustomException;

import static kgu.developers.apis.api.major.presentation.exception.MajorExceptionCode.MAJOR_DUPLICATED;

public class MajorDuplicatedException extends CustomException {
    public MajorDuplicatedException() {
        super(MAJOR_DUPLICATED);
    }
}
