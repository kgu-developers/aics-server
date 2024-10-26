package kgu.developers.globalutils.file;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDate;
import java.util.UUID;

import static java.time.format.DateTimeFormatter.ofPattern;

@Slf4j
@Component
@RequiredArgsConstructor
public class FileHandler {
	@Value("${file.multipart.maxFileSize}")
	private String MAX_FILE_SIZE;
	@Value("${file.upload.path}")
	private String BASE_PATH;
	@Value("#{'${file.allowed-extensions}'.split(',')}")
	private String[] allowedExtensions;

	/*
		예외 처리
	 */
	public boolean checkBeforeSave(MultipartFile file) {
		if (fileIsNull(file)) {
			log.error("파일이 널이나 존재하지 않음");
			return false;
		}

		if (isSizeBig(file)) {
			log.error("파일 크기가 너무 큼");
			return false;
		}

		if (isNotValidExtension(file.getOriginalFilename())) {
			log.error("파일 확장자가 유효하지 않음");
			return false;
		}

		return true;
	}

	public boolean checkAfterSaving(String path) {
		if (filePathIsNotValid(path)) {
			log.error("파일 저장 위치가 확인되지 않음");
			return false;
		}
		return true;
	}

	public boolean fileIsNull(MultipartFile file) {
		return file == null
			|| file.isEmpty()
			|| file.getOriginalFilename() == null;
	}

	public boolean isSizeBig(MultipartFile file) {
		return file.getSize() > Long.parseLong(MAX_FILE_SIZE);
	}

	public boolean isNotValidExtension(String fileName) {
		if (!fileName.contains(".")) {
			return false;
		}

		String extension = fileName.substring(fileName.lastIndexOf(".") + 1);
		for (String allow : allowedExtensions) {
			if (allow.equals(extension)) {
				return false;
			}
		}
		return true;
	}

	public boolean filePathIsNotValid(String filePath) {
		Path path = Path.of(filePath);
		return !Files.exists(path);
	}

	/*
		파일 처리
	 */
	public void deleteTmpFile(File file) throws IOException {
		Files.deleteIfExists(file.toPath());
	}

	public String makeFilePath(String domain, String originalFilename) {
		// TODO 경로 지정. 일단 로컬 테스트용
		String formatted = LocalDate.now().format(ofPattern("/yy/MM/dd/"));
		UUID uuid = UUID.randomUUID();
		String extension = originalFilename.substring(originalFilename.lastIndexOf("."));

		return BASE_PATH + domain + formatted + uuid + extension;
	}
}
