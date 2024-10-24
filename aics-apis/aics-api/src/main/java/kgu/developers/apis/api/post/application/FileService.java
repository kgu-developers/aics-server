package kgu.developers.apis.api.post.application;

import kgu.developers.apis.api.post.presentation.exception.FileIsNullException;
import kgu.developers.globalutils.file.application.FileHandler;
import kgu.developers.globalutils.file.response.FilePersistResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.UUID;

@Slf4j
@Service
@RequiredArgsConstructor
public class FileService {
	private final FileHandler fileHandler;

	public FilePersistResponse uploadFile(String domain, MultipartFile file) {
		validateFileIsNull(file);

		String originalFilename = file.getOriginalFilename();
		String extension = originalFilename.substring(originalFilename.lastIndexOf("."));

		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("/yy/MM/dd/");
		String formatted = LocalDate.now().format(formatter);
		UUID uuid = UUID.randomUUID();
		String filePath = "./" + domain + formatted + uuid + extension;
		log.info("Uploading file to {}", filePath);

		return fileHandler.saveFile(file, originalFilename, filePath);
	}

	private void validateFileIsNull(MultipartFile file) {
		if (file == null
			|| file.isEmpty()
			|| file.getOriginalFilename() == null) {
			log.error("File is null or empty");
			throw new FileIsNullException();
		}
	}

}
