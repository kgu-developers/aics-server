package kgu.developers.domain.file.exception;

import kgu.developers.common.exception.CustomException;

import static kgu.developers.domain.file.exception.FileDomainExceptionCode.FILE_STORE_FAILED;

public class FileStoreFailedException extends CustomException {
    public FileStoreFailedException() {
        super(FILE_STORE_FAILED);
    }
}
