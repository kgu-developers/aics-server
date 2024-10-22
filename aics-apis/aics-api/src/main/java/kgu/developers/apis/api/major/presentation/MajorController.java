package kgu.developers.apis.api.major.presentation;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import kgu.developers.apis.api.major.application.MajorService;
import kgu.developers.apis.api.major.presentation.request.MajorCreateRequest;
import kgu.developers.apis.api.major.presentation.response.MajorPersistResponse;
import lombok.RequiredArgsConstructor;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.http.HttpStatus.CREATED;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/majors")
@Tag(name = "Major", description = "전공 관리 API")
public class MajorController {
	private final MajorService majorService;

	@Operation(summary = "전공 생성 API", description = """
			- Description : 이 API는 중복 체크 후 전공을 생성합니다.
			- Assignee : 이신행
		""")
	@ApiResponse(responseCode = "201", content = @Content(schema = @Schema(implementation = MajorPersistResponse.class))
	)
	@PostMapping
	public ResponseEntity<MajorPersistResponse> createMajor(
		@RequestBody MajorCreateRequest request
	) {
		MajorPersistResponse response = majorService.createMajor(request);
		return ResponseEntity.status(CREATED).body(response);
	}
}
