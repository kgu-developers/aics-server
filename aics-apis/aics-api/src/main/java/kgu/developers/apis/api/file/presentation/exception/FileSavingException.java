package kgu.developers.apis.api.file.presentation.exception;

import kgu.developers.core.common.exception.CustomException;

import static kgu.developers.apis.api.file.presentation.exception.FileExceptionCode.FILE_SAVING_EXCEPTION;

public class FileSavingException extends CustomException {
	public FileSavingException() {
		super(FILE_SAVING_EXCEPTION);
	}
}
