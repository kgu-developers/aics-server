package kgu.developers.domain.file.application.query;

import kgu.developers.domain.file.domain.FileEntity;
import kgu.developers.domain.file.domain.FileRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class FileQueryService {
	private final FileRepository fileRepository;

	public FileEntity getFileById(Long id) {
		return fileRepository.findById(id).orElse(null);
	}

	public List<FileEntity> findAllByIds(List<Long> ids) {
		return fileRepository.findAllByIds(ids);
	}
}
