package kgu.developers.domain.file.infrastructure.repository;

import java.util.List;
import java.util.Optional;

import kgu.developers.domain.file.domain.FileModel;
import kgu.developers.domain.file.domain.FileRepository;
import kgu.developers.domain.file.infrastructure.entity.FileJpaEntity;
import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class FileRepositoryImpl implements FileRepository {
	private final JpaFileRepository jpaFileRepository;
	private final QueryFileRepository queryFileRepository;

	@Override
	public FileModel save(FileModel file) {
		return jpaFileRepository.save(
			FileJpaEntity.toEntity(file)
		).toDomain();
	}

	@Override
	public Optional<FileModel> findById(Long id) {
		return jpaFileRepository.findById(id)
			.map(FileJpaEntity::toDomain);
	}

	@Override
	public List<FileModel> findAllByIds(List<Long> ids) {
		return jpaFileRepository.findAllById(ids)
			.stream()
			.map(FileJpaEntity::toDomain)
			.toList();
	}

	@Override
	public Optional<String> findPhysicalPathById(Long id) {
		return queryFileRepository.findPhysicalPathById(id);
	}
}
