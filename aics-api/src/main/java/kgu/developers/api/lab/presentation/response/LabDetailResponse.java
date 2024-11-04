package kgu.developers.api.lab.presentation.response;

import static io.swagger.v3.oas.annotations.media.Schema.RequiredMode.REQUIRED;

import io.swagger.v3.oas.annotations.media.Schema;
import kgu.developers.domain.lab.domain.Lab;
import lombok.Builder;

@Builder
public record LabDetailResponse(
	@Schema(description = "연구실 ID", example = "99", requiredMode = REQUIRED)
	String name,

	@Schema(description = "연구실 위치", example = "1022", requiredMode = REQUIRED)
	String loc,

	@Schema(description = "연구실 사이트", example = "http://nninjoon.kyonggi.ac.kr", requiredMode = REQUIRED)
	String site
) {
	public static LabDetailResponse from(Lab lab) {
		return LabDetailResponse.builder()
			.name(lab.getName())
			.loc(lab.getLoc())
			.site(lab.getSite())
			.build();
	}
}
