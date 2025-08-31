package kgu.developers.domain.file.domain;

import java.util.List;
import java.util.Optional;

public interface FileRepository {
	FileEntity save(FileEntity fileEntity);
	Optional<FileEntity> findById(Long id);
	List<FileEntity> findAllByIds(List<Long> ids);
}
