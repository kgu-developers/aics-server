package kgu.developers.domain.file.infrastructure;

import java.util.Optional;

import kgu.developers.domain.file.domain.FileEntity;
import kgu.developers.domain.file.domain.FileRepository;
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

	@Override
	public Optional<FileEntity> findById(Long id) {
		return jpaFileRepository.findById(id);
	}
}
