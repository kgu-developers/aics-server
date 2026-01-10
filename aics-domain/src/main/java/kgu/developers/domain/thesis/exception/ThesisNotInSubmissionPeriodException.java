package kgu.developers.domain.thesis.exception;

import kgu.developers.common.exception.CustomException;

import static kgu.developers.domain.thesis.exception.ThesisDomainExceptionCode.THESIS_NOT_IN_SUBMISSION_PERIOD_EXCEPTION;

public class ThesisNotInSubmissionPeriodException extends CustomException {
    public ThesisNotInSubmissionPeriodException() {
        super(THESIS_NOT_IN_SUBMISSION_PERIOD_EXCEPTION);
    }
}
