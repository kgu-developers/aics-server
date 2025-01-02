package kgu.developers.admin.file.application;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import kgu.developers.admin.file.presentation.response.FilePathResponse;
import kgu.developers.domain.file.domain.FileDomain;
import kgu.developers.domain.file.domain.FileEntity;
import kgu.developers.domain.file.domain.FileRepository;
import kgu.developers.domain.file.infrastructure.FileStorageService;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class FileAdminService {
	private final FileStorageService fileStorageService;
	private final FileRepository fileRepository;

	public FilePathResponse saveFile(MultipartFile file, FileDomain fileDomain, Long directoryId) {
		String storedPath = fileStorageService.store(file, fileDomain, directoryId);
		FileEntity fileEntity = FileEntity.create(file.getOriginalFilename(), storedPath, file.getSize(),
			file.getContentType());
		FileEntity savedFile = fileRepository.save(fileEntity);
		return FilePathResponse.from(savedFile);
	}

}
