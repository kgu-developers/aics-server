package kgu.developers.admin.carousel.presentation.request;

import static io.swagger.v3.oas.annotations.media.Schema.RequiredMode.NOT_REQUIRED;

import io.swagger.v3.oas.annotations.media.Schema;

public record CarouselRequest(
	@Schema(description = "캐러셀 설명", example = "경기대학교 AI컴퓨터공학부 메인 이미지", requiredMode = NOT_REQUIRED)
	String text,

	@Schema(description = "캐러셀 이미지 링크", example = "https://www.kgu.ac.kr/", requiredMode = NOT_REQUIRED)
	String link
) {
}
