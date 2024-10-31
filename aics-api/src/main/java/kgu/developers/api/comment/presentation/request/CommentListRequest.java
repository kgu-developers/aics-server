package kgu.developers.api.comment.presentation.request;

import static io.swagger.v3.oas.annotations.media.Schema.RequiredMode.*;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public record CommentListRequest(
	@Schema(description = "게시물 아이디", example = "10", requiredMode = REQUIRED)
	@NotNull
	@Positive
	Long postId
) {
}
