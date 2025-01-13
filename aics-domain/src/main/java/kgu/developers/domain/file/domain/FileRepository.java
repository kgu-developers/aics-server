package kgu.developers.domain.file.domain;

import java.util.Optional;

public interface FileRepository {
	FileEntity save(FileEntity fileEntity);
	Optional<FileEntity> findById(Long id);
}
