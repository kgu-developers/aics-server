package kgu.developers.api.lab.presentation.response;

import static io.swagger.v3.oas.annotations.media.Schema.RequiredMode.NOT_REQUIRED;
import static io.swagger.v3.oas.annotations.media.Schema.RequiredMode.REQUIRED;

import io.swagger.v3.oas.annotations.media.Schema;
import kgu.developers.domain.file.application.response.FilePathResponse;
import kgu.developers.domain.lab.domain.Lab;
import lombok.Builder;

@Builder
public record LabDetailResponse(
	@Schema(description = "연구실 id", example = "1", requiredMode = REQUIRED)
	Long id,

	@Schema(description = "연구실 이름", example = "보안 연구실", requiredMode = REQUIRED)
	String name,

	@Schema(description = "연구실 위치", example = "8502, 8503", requiredMode = REQUIRED)
	String loc,

	@Schema(description = "연구실 사이트", example = "http://ailab.kyonggi.ac.kr", requiredMode = REQUIRED)
	String site,

	@Schema(description = "연구실 담당교수", example = "박민준", requiredMode = REQUIRED)
	String advisor,

	@Schema(description = "연구실 이미지 파일",
		example = "{"
			+ "\"id\": 1, "
			+ "\"physicalPath\": \"/files/lab/20250131/lab-A.png\"}",
		requiredMode = NOT_REQUIRED)
	FilePathResponse img
) {
	public static LabDetailResponse from(Lab lab) {
		return LabDetailResponse.builder()
			.id(lab.getId())
			.name(lab.getName())
			.loc(lab.getLoc())
			.site(lab.getSite())
			.advisor(lab.getAdvisor())
			.img(lab.getFile() != null ?
				FilePathResponse.from(lab.getFile()) : null)
			.build();
	}
}
