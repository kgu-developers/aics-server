package kgu.developers.admin.file.presentation;

import static kgu.developers.domain.file.domain.FileDomain.ABOUT;
import static kgu.developers.domain.file.domain.FileDomain.CAROUSEL;
import static kgu.developers.domain.file.domain.FileDomain.POST;
import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.MediaType.MULTIPART_FORM_DATA_VALUE;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.constraints.Positive;
import kgu.developers.admin.file.application.FileAdminFacade;
import kgu.developers.domain.file.application.response.FilePathResponse;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/files")
@Tag(name = "File", description = "파일 업로드 관리 API")
public class FileAdminController {
	private final FileAdminFacade fileAdminFacade;

	@Operation(summary = "게시글 파일 업로드 API", description = """
			- Description : 이 API는 게시글 첨부 파일을 업로드합니다. 한 게시글에 한 개의 파일만 업로드 가능합니다.
			- Assignee : 이한음
		""")
	@ApiResponse(responseCode = "201", content = @Content(schema = @Schema(implementation = FilePathResponse.class)))
	@PostMapping(value = "/post", consumes = MULTIPART_FORM_DATA_VALUE)
	public ResponseEntity<FilePathResponse> postFileUpload(
		@Parameter(description = "게시글 첨부 파일", content = @Content(mediaType = MULTIPART_FORM_DATA_VALUE))
		@RequestPart(value = "file") MultipartFile file,
		@Parameter(description = "게시글 ID", example = "2", required = true) @RequestParam @Positive Long postId
	) {
		FilePathResponse path = fileAdminFacade.saveFile(file, POST, postId);
		return ResponseEntity.status(CREATED).body(path);
	}

	@Operation(summary = "소개글 이미지 업로드 API", description = """
			- Description : 이 API는 소개글 이미지를 업로드 합니다. 리스폰스의 physicalPath를 받아서 사용하면 됩니다.
			- Assignee : 이한음
		""")
	@ApiResponse(responseCode = "201", content = @Content(schema = @Schema(implementation = FilePathResponse.class)))
	@PostMapping(value = "/about", consumes = MULTIPART_FORM_DATA_VALUE)
	public ResponseEntity<FilePathResponse> aboutFileUpload(
		@Parameter(description = "소개글 첨부 이미지", content = @Content(mediaType = MULTIPART_FORM_DATA_VALUE))
		@RequestPart(value = "file") MultipartFile file,
		@Parameter(description = "소개글 ID", example = "3", required = true) @RequestParam @Positive Long aboutId
	) {
		FilePathResponse path = fileAdminFacade.saveFile(file, ABOUT, aboutId);
		return ResponseEntity.status(CREATED).body(path);
	}

	@Operation(summary = "캐러셀 이미지 업로드 API", description = """
			- Description : 이 API는 캐러셀 이미지를 업로드합니다. 한 캐러셀에 한 개의 이미지만 업로드 가능합니다.
			- Assignee : 이한음
		""")
	@ApiResponse(responseCode = "201", content = @Content(schema = @Schema(implementation = FilePathResponse.class)))
	@PostMapping(value = "/carousel", consumes = MULTIPART_FORM_DATA_VALUE)
	public ResponseEntity<FilePathResponse> carouselFileUpload(
		@Parameter(description = "캐러셀 이미지", content = @Content(mediaType = MULTIPART_FORM_DATA_VALUE))
		@RequestPart(value = "file") MultipartFile file,
		@Parameter(description = "캐러셀 ID", example = "1", required = true) @RequestParam @Positive Long carouselId
	) {
		FilePathResponse path = fileAdminFacade.saveFile(file, CAROUSEL, carouselId);
		return ResponseEntity.status(CREATED).body(path);
	}
}
