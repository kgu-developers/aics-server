package kgu.developers.api.comment.presentation.request;

import static io.swagger.v3.oas.annotations.media.Schema.RequiredMode.REQUIRED;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public record CommentRequest(
	@Schema(description = "게시물 아이디", example = "1", requiredMode = REQUIRED)
	@NotNull
	@Positive
	Long postId,

	@Schema(description = "댓글 내용", example = "예시 코멘트입니다, 좋은 소식이네요!", requiredMode = REQUIRED)
	@NotBlank
	String content
) {
}
