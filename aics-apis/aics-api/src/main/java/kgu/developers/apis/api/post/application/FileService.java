package kgu.developers.apis.api.post.application;

import kgu.developers.apis.api.post.presentation.exception.FileIsNullException;
import kgu.developers.apis.api.post.presentation.response.FilePersistResponse;
import kgu.developers.core.domain.file.domain.FileEntity;
import kgu.developers.core.domain.file.domain.FileRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.UUID;

@Slf4j
@Service
@RequiredArgsConstructor
public class FileService {
	private final FileRepository fileRepository;

	@Transactional
	public FilePersistResponse uploadFile(String domain, MultipartFile file) {
		validateFileIsNull(file);

		String tempDir = System.getProperty("java.io.tmpdir");
		String tempFilePath = tempDir + "/" + file.getOriginalFilename();
		File tempFile = new File(tempFilePath);

		try {
			file.transferTo(tempFile);

			String originalFilename = tempFile.getName();
			String extension = originalFilename.substring(originalFilename.lastIndexOf("."));

			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("/yy/MM/dd/");
			String formatted = LocalDate.now().format(formatter);
			UUID uuid = UUID.randomUUID();
			// TODO 경로 지정 논의
			String basePath = "/KGU-Developers/uploaded";
			String filePath = basePath + domain + formatted + uuid + extension;
			log.info("Uploading file to {}", filePath);

			FilePersistResponse response = saveFile(tempFile, originalFilename, filePath);

			if (tempFile.delete()) {
				return response;
			} else {
				log.error("임시 파일 삭제 실패 {}", tempFilePath);
				return null;
			}
		} catch (IOException e) {
			log.error("파일 변환 중 IOException 발생 {}", e.getMessage());
			return null;
		}
	}

	private void validateFileIsNull(MultipartFile file) {
		if (file == null
			|| file.isEmpty()
			|| file.getOriginalFilename() == null) {
			log.error("File is null or empty");
			throw new FileIsNullException();
		}
	}

	@Transactional
	public FilePersistResponse saveFile(File file, String logicalName, String physicalPath) {
		try {
			File destinationFile = new File(physicalPath);
			if (!destinationFile.getParentFile().exists()) {
				destinationFile.getParentFile().mkdirs();
			}
			file.renameTo(destinationFile);

			FileEntity saved = fileRepository.save(
				FileEntity.create(logicalName, physicalPath)
			);

			return FilePersistResponse.builder()
				.id(saved.getId().toString())
				.build();

		} catch (Exception e) {
			log.error("파일 저장 중 Exception 발생 {}", e.getMessage());
			return null;
		}
	}

}
