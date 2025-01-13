package kgu.developers.admin.carousel.presentation;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Positive;
import kgu.developers.admin.carousel.presentation.request.CarouselRequest;
import kgu.developers.admin.carousel.presentation.response.CarouselPersistResponse;

@Tag(name = "Carousel", description = "캐러셀 관리자 API")
public interface CarouselAdminController {

	@Operation(summary = "캐러셀 생성 API", description = """
			- Description : 이 API는 캐러셀을 생성합니다.
			- Assignee : 이한음
		""")
	ResponseEntity<CarouselPersistResponse> createCarousel(
		@Parameter(
			description = "캐러셀에 개시할 이미지의 ID 입니다.",
			example = "1",
			required = true
		) @Positive @RequestParam Long fileId,
		@Parameter(
			description = "캐러셀 생성 request 객체 입니다."
		) @Valid @RequestBody(required = false) CarouselRequest request
	);

}
