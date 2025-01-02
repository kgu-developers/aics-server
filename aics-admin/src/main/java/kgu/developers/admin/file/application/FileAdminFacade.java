package kgu.developers.admin.file.application;

import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import kgu.developers.domain.file.application.response.FilePathResponse;
import kgu.developers.domain.file.application.command.FileCommandService;
import kgu.developers.domain.file.domain.FileDomain;
import kgu.developers.domain.file.domain.FileEntity;
import kgu.developers.domain.file.infrastructure.FileStorageService;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class FileAdminFacade {
	private final FileStorageService fileStorageService;
	private final FileCommandService fileCommandService;

	public FilePathResponse saveFile(MultipartFile file, FileDomain fileDomain, Long directoryId) {
		String storedPath = fileStorageService.store(file, fileDomain, directoryId);
		FileEntity savedFile = fileCommandService.saveFile(file, storedPath);
		return FilePathResponse.from(savedFile);
	}
}
