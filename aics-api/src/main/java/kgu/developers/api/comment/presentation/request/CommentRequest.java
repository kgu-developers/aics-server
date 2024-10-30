package kgu.developers.api.comment.presentation.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.Builder;

import static io.swagger.v3.oas.annotations.media.Schema.RequiredMode.REQUIRED;


@Builder
public record CommentRequest(
	@Schema(description = "댓글 내용", example = "예시 코멘트 입니다~~", requiredMode = REQUIRED)
	@NotBlank
	String content
) {
}
