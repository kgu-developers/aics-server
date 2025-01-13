package kgu.developers.api.carousel.presentation;

import org.springframework.http.ResponseEntity;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import kgu.developers.api.carousel.presentation.response.CarouselListResponse;

@Tag(name = "Carousel", description = "캐러셀 API")
public interface CarouselController {

	@Operation(summary = "캐러셀 리스트 조회 API", description = """
			- Description : 이 API는 모든 캐러셀 이미지와 기타 정보를 리스트로 조회합니다.
			- Assignee : 이한음
		""")
	@ApiResponse(
		responseCode = "200",
		content = @Content(schema = @Schema(implementation = CarouselListResponse.class)))
	ResponseEntity<CarouselListResponse> getCarousels();
}
