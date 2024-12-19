package kgu.developers.domain.file.exception;

import kgu.developers.common.exception.CustomException;

import static kgu.developers.domain.file.exception.FileDomainExceptionCode.FILE_NOT_FOUND;

public class FileNotFoundException extends CustomException {
    public FileNotFoundException() {
        super(FILE_NOT_FOUND);
    }
}
