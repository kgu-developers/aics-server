package kgu.developers.globalutils.file;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

@Component
@RequiredArgsConstructor
public class FileHandler {
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
}
