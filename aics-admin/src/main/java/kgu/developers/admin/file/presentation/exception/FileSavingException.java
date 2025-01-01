package kgu.developers.admin.file.presentation.exception;

import kgu.developers.common.exception.CustomException;

import static kgu.developers.admin.file.presentation.exception.FileExceptionCode.FILE_SAVING_ERROR;

public class FileSavingException extends CustomException {
	public FileSavingException() {
		super(FILE_SAVING_ERROR);
	}
}
