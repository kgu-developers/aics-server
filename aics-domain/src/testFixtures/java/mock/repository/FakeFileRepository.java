package mock.repository;

import java.util.*;
import java.util.concurrent.atomic.AtomicLong;

import kgu.developers.domain.file.domain.FileEntity;
import kgu.developers.domain.file.domain.FileRepository;

public class FakeFileRepository implements FileRepository {
	private final List<FileEntity> data = Collections.synchronizedList(new ArrayList<>());
	private final AtomicLong sequence = new AtomicLong(1);

	@Override
	public FileEntity save(FileEntity fileEntity) {
		FileEntity newFileEntity = FileEntity.builder()
			.id(sequence.getAndIncrement())
			.logicalName(fileEntity.getLogicalName())
			.physicalPath(fileEntity.getPhysicalPath())
			.fileSize(fileEntity.getFileSize())
			.extension(fileEntity.getExtension())
			.build();

		data.add(newFileEntity);
		return newFileEntity;
	}

	@Override
	public Optional<FileEntity> findById(Long id) {
		return data.stream()
			.filter(fileEntity -> fileEntity.getId().equals(id))
			.findFirst();
	}
}

