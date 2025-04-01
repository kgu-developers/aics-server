package kgu.developers.admin.post.presentation.request;

import static io.swagger.v3.oas.annotations.media.Schema.RequiredMode.NOT_REQUIRED;
import static io.swagger.v3.oas.annotations.media.Schema.RequiredMode.REQUIRED;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import kgu.developers.domain.post.domain.Category;
import lombok.Builder;

@Builder
public record PostCreateRequest(
	@Schema(description = "게시물 제목", example = "SW 부트캠프 4기 교육생 모집", requiredMode = REQUIRED)
	@NotBlank @Size(max = 100, message = "제목은 100자 이내여야 합니다.")
	String title,

	@Schema(description = "게시물 내용",
		example = """
			SW 부트캠프 4기 교육생 모집
			SW전문인재양성사업단에서는 SW부트캠프 4기 교육생을 모집합니다.
			관심있는 학생들의 많은 신청 바랍니다.
			- 모집기간 : 2024.10.07(월)~10.15(화)
			- 신청방법 :https://kebkyonggi.quv.kr/68
			""",
		requiredMode = REQUIRED)
	@NotBlank
	String content,

	@Schema(description = "게시물 카테고리", example = "NOTIFICATION", requiredMode = NOT_REQUIRED)
	Category category,

	@Schema(description = "게시글 고정 여부", example = "FALSE", requiredMode = REQUIRED)
	boolean isPinned
) {
}
