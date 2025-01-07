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
	String location,

	@Schema(description = "연구실 사이트", example = "http://ailab.kyonggi.ac.kr", requiredMode = REQUIRED)
	String site,

	@Schema(description = "연구실 담당교수", example = "박민준", requiredMode = REQUIRED)
	String professor,

	@Schema(description = "연구실 로고", example = "http://cs.kyonggi.ac.kr:8080/img/lab/20180209100533-%EC%9D%B8%EA%B3%B5%EC%A7%80%EB%8A%A5.png", requiredMode = REQUIRED)
	String img
) {
	public static LabDetailResponse from(Lab lab) {
		return LabDetailResponse.builder()
			.name(lab.getName())
			.location(lab.getLocation())
			.site(lab.getSite())
			.professor(lab.getProfessor())
			.img(lab.getFile().getPhysicalPath())
			.build();
	}
}
