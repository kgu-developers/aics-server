package kgu.developers.apis.api.file.presentation.exception;

import kgu.developers.core.common.exception.CustomException;

import static kgu.developers.apis.api.file.presentation.exception.FileExceptionCode.EXTENSION_IS_NOT_VALID;

public class ExtensionIsNotValidException extends CustomException {
	public ExtensionIsNotValidException() {
		super(EXTENSION_IS_NOT_VALID);
	}
}
