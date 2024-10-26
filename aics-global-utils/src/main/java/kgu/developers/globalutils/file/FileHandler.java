package kgu.developers.globalutils.file;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

@Slf4j
@Component
@RequiredArgsConstructor
public class FileHandler {
	// TODO yml 파일에 환경변수 처리
	private static final long MAX_FILE_SIZE = 1000 * 1024 * 1024;

	public boolean fileIsNull(MultipartFile file) {
		return file == null
			|| file.isEmpty()
			|| file.getOriginalFilename() == null;
	}

	public boolean isNotValidExtension(String fileName) {
		if (!fileName.contains(".")) {
			return false;
		}

		String[] extensions = {
			// 문서 파일
			"pdf", "doc", "docx", "xls", "xlsx", "ppt", "pptx",
			"txt", "odt", "hwp", "hwpx",
			// 이미지 파일
			"jpg", "jpeg", "png", "gif", "bmp", "svg",
			// 압축 파일
			"zip", "rar", "7z",
			// 미디어 파일
			"mp3", "wav", "mp4", "mov", "avi"
		};

		String extension = fileName.substring(fileName.lastIndexOf(".") + 1);
		System.out.println(extension);
		for (String allow : extensions) {
			if (allow.equals(extension)) {
				return false;
			}
		}
		return true;
	}

	public boolean isSizeBig(MultipartFile file) {
		return file.getSize() > MAX_FILE_SIZE;
	}

	public boolean filePathIsNotValid(String filePath) {
		Path path = Path.of(filePath);
		return !Files.exists(path);
	}

	public void deleteTmpFile(File file) throws IOException {
		Files.deleteIfExists(file.toPath());
	}

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

	public boolean checkAfterSaving(File file) {
		if (filePathIsNotValid(file.getPath())) {
			log.error("파일 저장 위치가 확인되지 않음");
			return false;
		}
		return true;
	}
}
