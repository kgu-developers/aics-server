package kgu.developers.domain.file.domain;

import java.util.List;
import java.util.Optional;

import kgu.developers.domain.file.infrastructure.entity.FileJpaEntity;

public interface FileRepository {
	FileModel save(FileModel fileEntity);
	Optional<FileModel> findById(Long id);
	List<FileModel> findAllByIds(List<Long> ids);
	Optional<String> findPhysicalPathById(Long id);
}
