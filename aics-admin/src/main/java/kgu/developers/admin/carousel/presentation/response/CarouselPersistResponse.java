package kgu.developers.admin.carousel.presentation.response;

import static io.swagger.v3.oas.annotations.media.Schema.RequiredMode.REQUIRED;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;

@Builder
public record CarouselPersistResponse(
	@Schema(description = "캐러셀 ID", example = "1", requiredMode = REQUIRED)
	Long id
) {
	public static CarouselPersistResponse of(Long id) {
		return CarouselPersistResponse.builder()
			.id(id)
			.build();
	}
}
