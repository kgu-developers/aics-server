package kgu.developers.admin.file.presentation;

import static org.springframework.http.MediaType.MULTIPART_FORM_DATA_VALUE;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import kgu.developers.domain.file.application.response.FilePathResponse;

@Tag(name = "File", description = "파일 업로드 관리 API")
public interface FileAdminController {

	@Operation(summary = "게시글 파일 업로드 API", description = """
			- Description : 이 API는 게시글 첨부 파일을 업로드합니다. 한 게시글에 한 개의 파일만 업로드 가능합니다.
			- Assignee : 이한음
		""")
	@ApiResponse(
		responseCode = "201",
		content = @Content(schema = @Schema(implementation = FilePathResponse.class)))
	ResponseEntity<FilePathResponse> postFileUpload(
		@Parameter(
			description = "게시글 첨부 파일",
			content = @Content(mediaType = MULTIPART_FORM_DATA_VALUE),
			required = true
		) @RequestPart(value = "file") MultipartFile file
	);

	@Operation(summary = "소개글 이미지 업로드 API", description = """
			- Description : 이 API는 소개글 이미지를 업로드 합니다. 리스폰스의 physicalPath를 받아서 사용하면 됩니다.
			- Assignee : 이한음
		""")
	@ApiResponse(
		responseCode = "201",
		content = @Content(schema = @Schema(implementation = FilePathResponse.class)))
	ResponseEntity<FilePathResponse> aboutFileUpload(
		@Parameter(
			description = "소개글 첨부 이미지",
			content = @Content(mediaType = MULTIPART_FORM_DATA_VALUE),
			required = true
		) @RequestPart(value = "file") MultipartFile file
	);

	@Operation(summary = "캐러셀 이미지 업로드 API", description = """
			- Description : 이 API는 캐러셀 이미지를 업로드합니다. 한 캐러셀에 한 개의 이미지만 업로드 가능합니다.
			- Assignee : 이한음
		""")
	@ApiResponse(
		responseCode = "201",
		content = @Content(schema = @Schema(implementation = FilePathResponse.class)))
	ResponseEntity<FilePathResponse> carouselFileUpload(
		@Parameter(
			description = "캐러셀 이미지",
			content = @Content(mediaType = MULTIPART_FORM_DATA_VALUE),
			required = true
		) @RequestPart(value = "file") MultipartFile file
	);

	@Operation(summary = "연구실 로고 이미지 업로드 API", description = """
			- Description : 이 API는 연구실 로고 이미지를 업로드합니다. 한 로고 이미지에 한 개의 이미지만 업로드 가능합니다.
			- Assignee : 박민준
		""")
	@ApiResponse(
		responseCode = "201",
		content = @Content(schema = @Schema(implementation = FilePathResponse.class)))
	ResponseEntity<FilePathResponse> labFileUpload(
		@Parameter(
			description = "연구실 로고 이미지",
			content = @Content(mediaType = MULTIPART_FORM_DATA_VALUE),
			required = true
		) @RequestPart(value = "file") MultipartFile file
	);

	@Operation(summary = "동아리 로고 이미지 업로드 API", description = """
			- Description : 이 API는 동아리 로고 이미지를 업로드합니다. 한 로고 이미지에 한 개의 이미지만 업로드 가능합니다.
			- Assignee : 박민준
		""")
	@ApiResponse(
		responseCode = "201",
		content = @Content(schema = @Schema(implementation = FilePathResponse.class)))
	ResponseEntity<FilePathResponse> clubFileUpload(
		@Parameter(
			description = "동아리 로고 이미지",
			content = @Content(mediaType = MULTIPART_FORM_DATA_VALUE),
			required = true
		) @RequestPart(value = "file") MultipartFile file
	);
}
