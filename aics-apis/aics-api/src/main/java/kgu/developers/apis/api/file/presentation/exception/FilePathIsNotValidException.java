package kgu.developers.apis.api.file.presentation.exception;

import kgu.developers.core.common.exception.CustomException;

import static kgu.developers.apis.api.file.presentation.exception.FileExceptionCode.FILE_PATH_IS_NOT_VALID;

public class FilePathIsNotValidException extends CustomException {
	public FilePathIsNotValidException() {
		super(FILE_PATH_IS_NOT_VALID);
	}
}
