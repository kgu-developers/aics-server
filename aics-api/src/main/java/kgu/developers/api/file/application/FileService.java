package kgu.developers.api.file.application;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import kgu.developers.api.file.presentation.response.FilePathResponse;
import kgu.developers.domain.file.domain.FileEntity;
import kgu.developers.domain.file.domain.FileRepository;
import kgu.developers.domain.file.infrastructure.FileStorageService;
import kgu.developers.globalutils.encryption.AesUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
public class FileService {
	private final FileRepository fileRepository;
	private final FileStorageService fileStorageService;

	public FilePathResponse saveFile(MultipartFile file) {
		String storedPath = fileStorageService.store(file);
		String encryptedPath = AesUtil.encrypt(storedPath);
		FileEntity fileEntity = FileEntity.create(file.getOriginalFilename(), encryptedPath, file.getSize(),
			file.getContentType());
		String savedPath = fileRepository.save(fileEntity).getPhysicalPath();
		return FilePathResponse.of(AesUtil.decrypt(savedPath));
	}

}
