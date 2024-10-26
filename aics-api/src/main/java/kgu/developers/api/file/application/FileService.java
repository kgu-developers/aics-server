package kgu.developers.api.file.application;

import kgu.developers.api.file.presentation.exception.FileSavingException;
import kgu.developers.api.file.presentation.response.FilePersistResponse;
import kgu.developers.domain.file.domain.FileEntity;
import kgu.developers.domain.file.domain.FileRepository;
import kgu.developers.globalutils.file.FileHandler;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;

@Slf4j
@Service
@RequiredArgsConstructor
@ComponentScan(basePackageClasses = kgu.developers.globalutils.file.FileHandler.class)
public class FileService {
	private final FileRepository fileRepository;
	private final FileHandler fileHandler;

	@Transactional
	public FilePersistResponse uploadFile(String domain, MultipartFile file) {
		if (!fileHandler.checkBeforeSave(file)) {
			throw new FileSavingException();
		}

		try {
			String tempDir = System.getProperty("java.io.tmpdir");
			String tempFilePath = tempDir + "/" + file.getOriginalFilename();
			File tempFile = new File(tempFilePath);

			file.transferTo(tempFile);
			String originalFilename = tempFile.getName();
			String filePath = fileHandler.makeFilePath(domain, originalFilename);
			FilePersistResponse response = saveFile(tempFile, originalFilename, filePath);

			if (!fileHandler.checkAfterSaving(filePath)) {
				throw new FileSavingException();
			}

			fileHandler.deleteTmpFile(tempFile);
			return response;
		} catch (IOException e) {
			log.error("파일 변환 중 IOException 발생 {}", e.getMessage());
			throw new FileSavingException();
		}
	}

	@Transactional
	public FilePersistResponse saveFile(File file, String logicalName, String physicalPath) throws IOException {
		File destinationFile = new File(physicalPath);
		if (!destinationFile.getParentFile().exists()) {
			destinationFile.getParentFile().mkdirs();
		}
		Files.move(file.toPath(), Path.of(physicalPath), StandardCopyOption.REPLACE_EXISTING);

		FileEntity entity = fileRepository.save(
			FileEntity.create(logicalName, physicalPath)
		);

		return FilePersistResponse.of(entity.getId().toString());
	}

}
