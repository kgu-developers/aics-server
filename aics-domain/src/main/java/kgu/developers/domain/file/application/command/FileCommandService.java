package kgu.developers.domain.file.application.command;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import kgu.developers.domain.file.domain.FileModel;
import kgu.developers.domain.file.domain.FileRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class FileCommandService {
	private final FileRepository fileRepository;

	public FileModel saveFile(MultipartFile file, String storedPath) {
		FileModel fileEntity = FileModel.create(file.getOriginalFilename(), storedPath, file.getSize(),
			file.getContentType());
		return fileRepository.save(fileEntity);
	}
}
