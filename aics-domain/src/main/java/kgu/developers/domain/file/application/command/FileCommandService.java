package kgu.developers.domain.file.application.command;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import kgu.developers.domain.file.domain.FileEntity;
import kgu.developers.domain.file.domain.FileRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class FileCommandService {
	private final FileRepository fileRepository;

	public FileEntity saveFile(MultipartFile file, String storedPath) {
		FileEntity fileEntity = FileEntity.create(file.getOriginalFilename(), storedPath, file.getSize(),
			file.getContentType());
		return fileRepository.save(fileEntity);
	}
}
