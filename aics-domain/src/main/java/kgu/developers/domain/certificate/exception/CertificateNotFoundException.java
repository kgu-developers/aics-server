package kgu.developers.domain.certificate.exception;

import kgu.developers.common.exception.CustomException;

import static kgu.developers.domain.certificate.exception.CertificateDomainExceptionCode.CERTIFICATE_NOT_FOUND;

public class CertificateNotFoundException extends CustomException {
    public CertificateNotFoundException() {
        super(CERTIFICATE_NOT_FOUND);
    }
}
