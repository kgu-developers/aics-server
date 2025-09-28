package mock.repository;

import java.util.*;
import java.util.concurrent.atomic.AtomicLong;

import kgu.developers.domain.file.domain.FileModel;
import kgu.developers.domain.file.domain.FileRepository;
import kgu.developers.domain.file.infrastructure.entity.FileJpaEntity;

public class FakeFileRepository implements FileRepository {
	private final List<FileJpaEntity> data = Collections.synchronizedList(new ArrayList<>());
	private final AtomicLong sequence = new AtomicLong(1);

	@Override
	public FileModel save(FileModel file) {
		FileJpaEntity newFileJpaEntity = FileJpaEntity.builder()
			.id(sequence.getAndIncrement())
			.logicalName(file.getLogicalName())
			.physicalPath(file.getPhysicalPath())
			.fileSize(file.getFileSize())
			.extension(file.getExtension())
			.build();

		data.add(newFileJpaEntity);
		return newFileJpaEntity.toDomain();
	}

	@Override
	public Optional<FileModel> findById(Long id) {
		return data.stream()
			.filter(fileEntity -> fileEntity.getId().equals(id))
			.map(FileJpaEntity::toDomain)
			.findFirst();
	}

	@Override
	public List<FileModel> findAllByIds(List<Long> ids) {
		return data.stream()
				.filter(file -> ids.contains(file.getId()))
				.map(FileJpaEntity::toDomain)
				.toList();
	}

	@Override
	public Optional<String> findPhysicalPathById(Long id) {
		return data.stream()
			.filter(fileEntity -> fileEntity.getId().equals(id))
			.map(FileJpaEntity::getPhysicalPath)
			.findFirst();
	}
}

