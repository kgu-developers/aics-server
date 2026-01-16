package kgu.developers.domain.certificate.exception;

import kgu.developers.common.exception.CustomException;

import static kgu.developers.domain.certificate.exception.CertificateDomainExceptionCode.CERTIFICATE_SUBMISSION_PERIOD_CLOSED;


public class CertificateNotInSubmissionPeriodException extends CustomException {
    public CertificateNotInSubmissionPeriodException() {
        super(CERTIFICATE_SUBMISSION_PERIOD_CLOSED);
    }
}
