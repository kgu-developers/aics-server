package kgu.developers.domain.thesis.exception;

import kgu.developers.common.exception.CustomException;

import static kgu.developers.domain.thesis.exception.ThesisDomainExceptionCode.THESIS_SUBMISSION_PERIOD_CLOSED;

public class ThesisNotInSubmissionPeriodException extends CustomException {
    public ThesisNotInSubmissionPeriodException() {
        super(THESIS_SUBMISSION_PERIOD_CLOSED);
    }
}
