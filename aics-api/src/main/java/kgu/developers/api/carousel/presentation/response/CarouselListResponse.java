package kgu.developers.api.carousel.presentation.response;

import static io.swagger.v3.oas.annotations.media.Schema.RequiredMode.REQUIRED;

import java.util.List;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;

@Builder
public record CarouselListResponse(
	@Schema(description = "캐러셀 리스트",
		example = """
			[{
				"id": 1,
				"text": "경기대학교 AI컴퓨터공학부 메인 이미지",
            	"link": "https://www.kgu.ac.kr/",
                "file": {
                    "id": 1,
                    "physicalPath": "/cloud/carousel/3/2025-curriculum"
                }
            }]
			""",
		requiredMode = REQUIRED)
	List<CarouselResponse> contents
) {
	public static CarouselListResponse from(List<CarouselResponse> carousels) {
		return CarouselListResponse.builder()
			.contents(carousels)
			.build();
	}
}
