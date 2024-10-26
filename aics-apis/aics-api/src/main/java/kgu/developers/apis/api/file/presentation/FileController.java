package kgu.developers.apis.api.file.presentation;

import io.swagger.v3.oas.annotations.Hidden;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jakarta.servlet.http.HttpServletRequest;
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
@RequestMapping("/files")
public class FileController {
	private final FileService fileService;

	@Hidden
	@Operation(summary = "파일 업로드 API", description = """
			- Description : 이 API는 파일을 저장합니다. MultiPartFile을 Body에 넣어서 전달해주세요.
			- Assignee : 이신행
		""")
	@ApiResponse(responseCode = "201", content = @Content(schema = @Schema(implementation = FilePersistResponse.class)))
	@PostMapping
	public ResponseEntity<FilePersistResponse> uploadFile(@RequestParam("file") MultipartFile file,
														  HttpServletRequest request) {
		// 업로드한 API의 도메인을 저장시에 도메인으로 사용
		String domain = request.getRequestURI().split("/")[1];
		FilePersistResponse response = fileService.uploadFile(domain, file);
		return ResponseEntity.status(CREATED).body(response);
	}
}
