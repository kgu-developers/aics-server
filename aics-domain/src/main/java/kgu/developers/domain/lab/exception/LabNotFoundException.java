package kgu.developers.domain.lab.exception;

import static kgu.developers.domain.lab.exception.LabDomainExceptionCode.LAB_NOT_FOUND;

import kgu.developers.common.exception.CustomException;

public class LabNotFoundException extends CustomException {
	public LabNotFoundException() {
		super(LAB_NOT_FOUND);
	}
}
