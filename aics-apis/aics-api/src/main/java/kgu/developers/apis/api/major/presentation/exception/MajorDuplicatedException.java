package kgu.developers.apis.api.major.presentation.exception;

import kgu.developers.core.common.exception.CustomException;

public class MajorDuplicatedException extends CustomException {
    public MajorDuplicatedException() {
        super(MajorExceptionCode.MAJOR_DUPLICATED);
    }
}
