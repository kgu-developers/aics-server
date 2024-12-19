package kgu.developers.domain.file.exception;

import kgu.developers.common.exception.CustomException;

import static kgu.developers.domain.file.exception.FileDomainExceptionCode.FILE_DIRECTORY_CREATION_FAILED;

public class FileDirectoryCreationFailedException extends CustomException {
    public FileDirectoryCreationFailedException() {
        super(FILE_DIRECTORY_CREATION_FAILED);
    }
}
