package kgu.developers.api.thesis.presentation;

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
import kgu.developers.api.thesis.presentation.request.ThesisSubmitRequest;
import kgu.developers.api.thesis.presentation.response.ThesisPersistResponse;

@Tag(name = "Thesis", description = "졸업 논문 API")
public interface ThesisController {

	@Operation(
		summary = "졸업 논문 파일 제출 API", description = """
		    - Description : 이 API는 졸업 논문 파일을 제출합니다.
		    - Assignee : 이한음
		""")
	@ApiResponse(
		responseCode = "201",
		content = @Content(schema = @Schema(implementation = ThesisPersistResponse.class)))
	ResponseEntity<ThesisPersistResponse> submitThesisAndSaveFile(
		@Parameter(
			description = "졸업 논문 첨부 파일",
			content = @Content(mediaType = MULTIPART_FORM_DATA_VALUE),
			required = true
		) @RequestPart(value = "file") MultipartFile file,
		@RequestPart ThesisSubmitRequest request
		);


}
