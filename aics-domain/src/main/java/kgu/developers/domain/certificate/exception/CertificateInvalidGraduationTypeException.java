package kgu.developers.domain.certificate.exception;

import kgu.developers.common.exception.CustomException;

import static kgu.developers.domain.certificate.exception.CertificateDomainExceptionCode.CERTIFICATE_INVALID_GRADUATION_TYPE_EXCEPTION;

public class CertificateInvalidGraduationTypeException extends CustomException {
    public CertificateInvalidGraduationTypeException() {
        super(CERTIFICATE_INVALID_GRADUATION_TYPE_EXCEPTION);
    }
}
