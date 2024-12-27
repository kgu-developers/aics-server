package kgu.developers.api.file.application;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import kgu.developers.api.file.presentation.response.FilePathResponse;
import kgu.developers.domain.file.domain.FileDomain;
import kgu.developers.domain.file.domain.FileEntity;
import kgu.developers.domain.file.domain.FileRepository;
import kgu.developers.domain.file.infrastructure.FileStorageService;
import kgu.developers.globalutils.encryption.AesUtil;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class FileService {
	private final FileRepository fileRepository;
	private final FileStorageService fileStorageService;

	public FilePathResponse saveFile(MultipartFile file, FileDomain fileDomain, Long directoryId) {
		String storedPath = fileStorageService.store(file, fileDomain, directoryId);
		FileEntity fileEntity = FileEntity.create(file.getOriginalFilename(), storedPath, file.getSize(),
			file.getContentType());
		FileEntity savedFile = fileRepository.save(fileEntity);
		return FilePathResponse.from(savedFile);
	}

}
