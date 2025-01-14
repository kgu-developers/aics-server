package kgu.developers.admin.carousel.presentation;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Positive;
import kgu.developers.admin.carousel.presentation.request.CarouselRequest;
import kgu.developers.admin.carousel.presentation.response.CarouselPersistResponse;

@Tag(name = "Carousel", description = "캐러셀 관리자 API")
public interface CarouselAdminController {

	@Operation(summary = "캐러셀 저장 API", description = """
			- Description : 이 API는 캐러셀 이미지와 기타 정보를 저장합니다.
			- Assignee : 이한음
		""")
	@ApiResponse(
		responseCode = "201",
		content = @Content(schema = @Schema(implementation = CarouselPersistResponse.class)))
	ResponseEntity<CarouselPersistResponse> createCarousel(
		@Parameter(
			description = "캐러셀에 개시할 이미지의 ID 입니다.",
			example = "1",
			required = true
		) @Positive @RequestParam Long fileId,
		@Parameter(
			description = "캐러셀 생성 request 객체 입니다."
		) @Valid @RequestBody CarouselRequest request
	);

	@Operation(summary = "캐러셀 삭제 API", description = """
			- Description : 이 API는 캐러셀을 삭제합니다.
			- Assignee : 이한음
		""")
	@ApiResponse(responseCode = "204")
	ResponseEntity<Void> deleteCarousel(
		@Parameter(
			description = "캐러셀 ID는 URL 경로 변수 입니다.",
			example = "1",
			required = true
		) @Positive @PathVariable Long id
	);
}
