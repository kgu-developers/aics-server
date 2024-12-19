package kgu.developers.domain.file.exception;

import kgu.developers.common.exception.CustomException;

import static kgu.developers.domain.file.exception.FileDomainExceptionCode.FILE_PATH_INVALID;

public class FilePathInvalidException extends CustomException {
    public FilePathInvalidException() {
        super(FILE_PATH_INVALID);
    }
}
