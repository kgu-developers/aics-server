package kgu.developers.domain.file.application.query;

import org.springframework.stereotype.Service;

import kgu.developers.domain.file.domain.FileEntity;
import kgu.developers.domain.file.domain.FileRepository;
import kgu.developers.domain.file.exception.FileNotFoundException;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class FileQueryService {
	private final FileRepository fileRepository;

	public FileEntity getFileById(Long id) {
		return fileRepository.findById(id).orElseThrow(FileNotFoundException::new);
	}
}
