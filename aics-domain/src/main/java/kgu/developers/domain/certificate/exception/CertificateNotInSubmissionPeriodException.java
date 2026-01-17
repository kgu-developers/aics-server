package kgu.developers.domain.certificate.exception;

import kgu.developers.common.exception.CustomException;

import static kgu.developers.domain.certificate.exception.CertificateDomainExceptionCode.CERTIFICATE_NOT_IN_SUBMISSION_PERIOD;


public class CertificateNotInSubmissionPeriodException extends CustomException {
    public CertificateNotInSubmissionPeriodException() {
        super(CERTIFICATE_NOT_IN_SUBMISSION_PERIOD);
    }
}
