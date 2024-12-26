package kgu.developers.domain.file.exception;

import static kgu.developers.domain.file.exception.FileDomainExceptionCode.*;

import kgu.developers.common.exception.CustomException;

public class NotSupportedFileExtensionException extends CustomException {
	public NotSupportedFileExtensionException() {
		super(NOT_SUPPORTED_FILE_EXTENSION);
	}
}
