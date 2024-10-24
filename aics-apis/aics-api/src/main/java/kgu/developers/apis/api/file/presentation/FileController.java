package kgu.developers.apis.api.file.presentation;

import kgu.developers.apis.api.file.application.FileService;
import kgu.developers.apis.api.file.presentation.response.FilePersistResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import static org.springframework.http.HttpStatus.CREATED;

@RestController
@RequiredArgsConstructor
@RequestMapping("/posts")
public class FileController {
	private final FileService fileService;

	// TODO: 추후에 Post 업로드 기능 구현 시에 합치기
	@PostMapping("/file-upload")
	public ResponseEntity<FilePersistResponse> uploadFile(@RequestParam("file") MultipartFile file) {
		FilePersistResponse response = fileService.uploadFile("posts", file);
		return ResponseEntity.status(CREATED).body(response);
	}
}
