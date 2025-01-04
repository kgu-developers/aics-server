package kgu.developers.api.lab.presentation.response;

import static io.swagger.v3.oas.annotations.media.Schema.RequiredMode.REQUIRED;

import io.swagger.v3.oas.annotations.media.Schema;
import kgu.developers.domain.lab.domain.Lab;
import lombok.Builder;

@Builder
public record LabDetailResponse(
	@Schema(description = "연구실 이름", example = "보안 연구실", requiredMode = REQUIRED)
	String name,

	@Schema(description = "연구실 위치", example = "8502, 8503", requiredMode = REQUIRED)
	String loc,

	@Schema(description = "연구실 사이트", example = "http://ailab.kyonggi.ac.kr", requiredMode = REQUIRED)
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
