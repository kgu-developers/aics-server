package kgu.developers.apis.api.file.presentation.exception;

import kgu.developers.core.common.exception.CustomException;

import static kgu.developers.apis.api.file.presentation.exception.FileExceptionCode.FILE_IS_NULL_EXCEPTION;

public class FileIsNullException extends CustomException {
	public FileIsNullException() {
		super(FILE_IS_NULL_EXCEPTION);
	}
}
