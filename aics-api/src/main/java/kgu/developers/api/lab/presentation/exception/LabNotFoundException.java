package kgu.developers.api.lab.presentation.exception;

import static kgu.developers.api.lab.presentation.exception.LabExceptionCode.LAB_NOT_FOUND;

import kgu.developers.common.exception.CustomException;

public class LabNotFoundException extends CustomException {
	public LabNotFoundException() {
		super(LAB_NOT_FOUND);
	}
}
