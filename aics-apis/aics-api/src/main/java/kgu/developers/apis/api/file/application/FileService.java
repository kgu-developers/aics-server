package kgu.developers.apis.api.file.application;

import kgu.developers.apis.api.file.presentation.exception.ExtensionIsNotValidException;
import kgu.developers.apis.api.file.presentation.exception.FileIsNullException;
import kgu.developers.apis.api.file.presentation.exception.FileIsTooBigException;
import kgu.developers.apis.api.file.presentation.exception.FilePathIsNotValidException;
import kgu.developers.apis.api.file.presentation.response.FilePersistResponse;
import kgu.developers.core.domain.file.domain.FileEntity;
import kgu.developers.core.domain.file.domain.FileRepository;
import kgu.developers.globalutils.file.FileHandler;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.util.UUID;

import static java.time.format.DateTimeFormatter.ofPattern;

@Slf4j
@Service
@RequiredArgsConstructor
public class FileService {
	private final FileRepository fileRepository;
	private final FileHandler fileHandler;

	@Transactional
	public FilePersistResponse uploadFile(String domain, MultipartFile file) {
		if (fileHandler.fileIsNull(file)) {
			log.error("파일이 널이나 존재하지 않음");
			throw new FileIsNullException();
		}

		if (fileHandler.isSizeBig(file)) {
			log.error("파일 크기가 너무 큼");
			throw new FileIsTooBigException();
		}

		String tempDir = System.getProperty("java.io.tmpdir");
		String tempFilePath = tempDir + "/" + file.getOriginalFilename();
		File tempFile = new File(tempFilePath);

		try {
			file.transferTo(tempFile);

			if (fileHandler.isNotValidExtension(tempFile.getName())) {
				log.error("파일 확장자가 유효하지 않음");
				throw new ExtensionIsNotValidException();
			}

			// TODO 경로 지정. 일단 로컬 테스트용
			String basePath = "/Users/snhng/uploaded-demo/";
			String formatted = LocalDate.now().format(ofPattern("/yy/MM/dd/"));
			UUID uuid = UUID.randomUUID();
			String originalFilename = tempFile.getName();
			String extension = originalFilename.substring(originalFilename.lastIndexOf("."));
			String filePath = basePath + domain + formatted + uuid + extension;

			FilePersistResponse response = saveFile(tempFile, originalFilename, filePath);

			if (fileHandler.filePathIsNotValid(filePath)) {
				log.error("파일 저장 위치가 확인되지 않음");
				throw new FilePathIsNotValidException();
			}

			fileHandler.deleteTmpFile(tempFile);
			return response;
		} catch (IOException e) {
			log.error("파일 변환 중 IOException 발생 {}", e.getMessage());
			return null;
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

			FileEntity entity = fileRepository.save(
				FileEntity.create(logicalName, physicalPath)
			);

			return FilePersistResponse.of(entity.getId().toString());
		} catch (Exception e) {
			log.error("파일 저장 중 Exception 발생 {}", e.getMessage());
			return null;
		}
	}

}
