package kgu.developers.api.carousel.presentation.response;

import static io.swagger.v3.oas.annotations.media.Schema.RequiredMode.NOT_REQUIRED;
import static io.swagger.v3.oas.annotations.media.Schema.RequiredMode.REQUIRED;

import io.swagger.v3.oas.annotations.media.Schema;
import kgu.developers.domain.carousel.domain.Carousel;
import kgu.developers.domain.file.application.response.FilePathResponse;
import lombok.Builder;

@Builder
public record CarouselResponse(
	@Schema(description = "캐러셀 ID", example = "1", requiredMode = REQUIRED)
	Long id,

	@Schema(description = "캐러셀 설명", example = "경기대학교 AI컴퓨터공학부 메인 이미지", requiredMode = NOT_REQUIRED)
	String text,

	@Schema(description = "캐러셀 이미지 링크", example = "https://www.kgu.ac.kr/", requiredMode = NOT_REQUIRED)
	String link,

	@Schema(description = "첨부 파일 정보",
		example = "{\"id\": 1, "
			+ "\"physicalPath\": \"/files/2025-curriculum\"}",
		requiredMode = NOT_REQUIRED)
	FilePathResponse file
) {
	public static CarouselResponse of(Carousel carousel, String physicalPath) {
		return CarouselResponse.builder()
			.id(carousel.getId())
			.text(carousel.getText())
			.link(carousel.getLink())
			.file(carousel.getFileId() != null ? FilePathResponse.of(carousel.getFileId(), physicalPath) : null)
			.build();
	}
}
