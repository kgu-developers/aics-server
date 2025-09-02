package kgu.developers.domain.file.infrastructure;

import java.util.List;
import java.util.Optional;

import kgu.developers.domain.file.domain.FileEntity;
import kgu.developers.domain.file.domain.FileRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class FileRepositoryImpl implements FileRepository {
	private final JpaFileRepository jpaFileRepository;
	private final QueryFileRepository queryFileRepository;

	@Override
	public FileEntity save(FileEntity fileEntity) {
		return jpaFileRepository.save(fileEntity);
	}

	@Override
	public Optional<FileEntity> findById(Long id) {
		return jpaFileRepository.findById(id);
	}

	@Override
	public List<FileEntity> findAllByIds(List<Long> ids) {
		return jpaFileRepository.findAllById(ids);
	}

	@Override
	public Optional<String> findPhysicalPathById(Long id) {
		return queryFileRepository.findPhysicalPathById(id);
	}
}
