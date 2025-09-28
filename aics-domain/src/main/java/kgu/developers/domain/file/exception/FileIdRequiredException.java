package kgu.developers.domain.file.exception;

import static kgu.developers.domain.file.exception.FileDomainExceptionCode.FILE_ID_REQUIRED;

import kgu.developers.common.exception.CustomException;

public class FileIdRequiredException extends CustomException {
	public FileIdRequiredException() {
		super(FILE_ID_REQUIRED);
	}
}
