package kgu.developers.globalutils.file.application;

import kgu.developers.globalutils.file.response.FilePersistResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

@Component
@RequiredArgsConstructor
public class FileHandler {
	public FilePersistResponse saveFile(MultipartFile file, String originalFilename, String filePath) {
		// TODO
		return null;
	}
}
