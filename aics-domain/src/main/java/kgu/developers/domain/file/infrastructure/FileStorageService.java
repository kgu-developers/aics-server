package kgu.developers.domain.file.infrastructure;

import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

public interface FileStorageService {

    String store(MultipartFile file);

    Resource loadAsResource(String filename);

    void deleteAll();
}
