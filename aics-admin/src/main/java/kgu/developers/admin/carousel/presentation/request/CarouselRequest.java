package kgu.developers.admin.carousel.presentation.request;

import static io.swagger.v3.oas.annotations.media.Schema.RequiredMode.NOT_REQUIRED;

import org.hibernate.validator.constraints.URL;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Size;

public record CarouselRequest(
	@Schema(description = "캐러셀 설명", example = "경기대학교 AI컴퓨터공학부 메인 이미지", requiredMode = NOT_REQUIRED)
	@Size(max = 255, message = "설명은 255자를 초과할 수 없습니다")
	String text,

	@Schema(description = "캐러셀 이미지 링크", example = "https://www.kgu.ac.kr/", requiredMode = NOT_REQUIRED)
	@URL(message = "올바른 URL 형식이어야 합니다")
	String link
) {
}
