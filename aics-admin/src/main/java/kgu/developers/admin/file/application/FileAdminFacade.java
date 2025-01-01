package kgu.developers.admin.file.application;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import kgu.developers.admin.file.presentation.response.FilePathResponse;
import kgu.developers.domain.file.FileSaveService;
import kgu.developers.domain.file.domain.FileDomain;
import kgu.developers.domain.file.domain.FileEntity;
import kgu.developers.domain.file.domain.FileRepository;
import kgu.developers.domain.file.infrastructure.FileStorageService;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class FileAdminFacade {
	private final FileStorageService fileStorageService;
	private final FileSaveService fileSaveService;

	public FilePathResponse saveFile(MultipartFile file, FileDomain fileDomain, Long directoryId) {
		String storedPath = fileStorageService.store(file, fileDomain, directoryId);
		FileEntity savedFile = fileSaveService.saveFile(file, storedPath);
		return FilePathResponse.from(savedFile);
	}

}
