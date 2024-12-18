package kgu.developers.api.file.presentation;

import static org.springframework.http.HttpStatus.*;
import static org.springframework.http.MediaType.*;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import kgu.developers.api.file.application.FileService;
import kgu.developers.api.file.presentation.response.FilePathResponse;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/files")
public class FileController {
	private final FileService fileService;

//	@Hidden
//	@Operation(summary = "파일 업로드 API", description = """
//			- Description : 이 API는 파일을 저장합니다. MultiPartFile을 Body에 넣어서 전달해주세요.
//			- Assignee : 이신행
//		""")
//	@ApiResponse(responseCode = "201", content = @Content(schema = @Schema(implementation = FilePersistResponse.class)))
//	@PostMapping
//	public ResponseEntity<FilePersistResponse> uploadFile(
//		@RequestParam("file") MultipartFile file,
//		HttpServletRequest request
//	) {
//		String domain = request.getRequestURI().split("/")[1];
//		FilePersistResponse response = fileService.uploadFile(domain, file);
//		return ResponseEntity.status(CREATED).body(response);
//	}

	@PostMapping(consumes = MULTIPART_FORM_DATA_VALUE)
	public ResponseEntity<FilePathResponse> fileUploadTest(
		@Parameter(description = "첨부 파일", content = @Content(mediaType = MULTIPART_FORM_DATA_VALUE))
		@RequestPart(value = "file", required = false) MultipartFile file
	) {
		FilePathResponse path = fileService.saveFile(file);
		return ResponseEntity.status(CREATED).body(path);
	}


}
