package kgu.developers.admin.club.presentation.request;

import static io.swagger.v3.oas.annotations.media.Schema.RequiredMode.NOT_REQUIRED;
import static io.swagger.v3.oas.annotations.media.Schema.RequiredMode.REQUIRED;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public record ClubUpdateRequest(
	@Schema(description = "동아리 이름", example = "C-Lab", requiredMode = REQUIRED)
	@NotBlank
	@Size(max = 15)
	String name,

	@Schema(description = "동아리 설명", example = "경기대학교 AI컴퓨터공학부 개발동아리입니다.", requiredMode = REQUIRED)
	@NotBlank
	@Size(max = 50)
	String description,

	@Schema(description = "동아리 홈페이지", example = "https://www.clab.page/", requiredMode = NOT_REQUIRED)
	@Pattern(regexp = "^(http|https)://.*$", message = "URL은 http:// 또는 https://으로 시작해야 합니다.")
	String site,

	@Schema(description = "파일 ID", example = "1")
	Long fileId
) {
}
