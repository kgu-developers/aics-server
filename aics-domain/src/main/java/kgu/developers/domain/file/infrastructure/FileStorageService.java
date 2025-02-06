package kgu.developers.domain.file.infrastructure;

import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

import kgu.developers.domain.file.domain.FileDomain;

public interface FileStorageService {

    String store(MultipartFile file, FileDomain fileDomain);

    Resource loadAsResource(String filename);

    void deleteAll();
}
