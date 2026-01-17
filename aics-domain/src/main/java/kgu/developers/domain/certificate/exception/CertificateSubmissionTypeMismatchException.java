package kgu.developers.domain.certificate.exception;

import kgu.developers.common.exception.CustomException;

import static kgu.developers.domain.certificate.exception.CertificateDomainExceptionCode.CERTIFICATE_SUBMISSION_TYPE_MISMATCH;

public class CertificateSubmissionTypeMismatchException extends CustomException {
    public CertificateSubmissionTypeMismatchException() {
        super(CERTIFICATE_SUBMISSION_TYPE_MISMATCH);
    }
}
