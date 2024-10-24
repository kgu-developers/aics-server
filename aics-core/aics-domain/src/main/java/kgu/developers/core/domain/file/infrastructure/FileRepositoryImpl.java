package kgu.developers.core.domain.file.infrastructure;

import kgu.developers.core.domain.file.domain.FileEntity;
import kgu.developers.core.domain.file.domain.FileRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class FileRepositoryImpl implements FileRepository {
	private final JpaFileRepository jpaFileRepository;

	@Override
	public FileEntity save(FileEntity fileEntity) {
		return jpaFileRepository.save(fileEntity);
	}
}
