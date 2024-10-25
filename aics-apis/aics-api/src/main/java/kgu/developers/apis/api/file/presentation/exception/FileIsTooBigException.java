package kgu.developers.apis.api.file.presentation.exception;

import kgu.developers.core.common.exception.CustomException;

import static kgu.developers.apis.api.file.presentation.exception.FileExceptionCode.FILE_IS_TOO_BIG;

public class FileIsTooBigException extends CustomException {
	public FileIsTooBigException() {
		super(FILE_IS_TOO_BIG);
	}
}
