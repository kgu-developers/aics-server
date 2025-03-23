package kgu.developers.admin.lab.presentation.request;

import static io.swagger.v3.oas.annotations.media.Schema.RequiredMode.REQUIRED;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Builder;

@Builder
public record LabCreateRequest(
	@Schema(description = "연구실 이름", example = "인공지능연구실", requiredMode = REQUIRED)
	@NotBlank
	@Size(max = 15)
	String name,

	@Schema(description = "연구실 위치", example = "8502, 8503", requiredMode = REQUIRED)
	@NotBlank
	@Size(max = 10)
	String loc,

	@Schema(description = "연구실 홈페이지", example = "http://ailab.kyonggi.ac.kr", requiredMode = Schema.RequiredMode.REQUIRED)
	@NotBlank
	@Pattern(regexp = "^(http|https)://[a-zA-Z0-9.-]+\\.kyonggi\\.ac\\.kr$", message = "URL은 http:// 또는 https://으로 시작하고 kyonggi.ac.kr 도메인으로 끝나야 합니다.")
	String site,

	@Schema(description = "연구실 담당교수", example = "박민준", requiredMode = REQUIRED)
	@NotBlank
	@Size(max = 15)
	String advisor
) {
}
