package kgu.developers.api.comment.presentation.response;

import static io.swagger.v3.oas.annotations.media.Schema.RequiredMode.REQUIRED;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;

@Builder
public record CommentPersistResponse(
	@Schema(description = "댓글 id", example = "1", requiredMode = REQUIRED)
	Long commentId
) {
	public static CommentPersistResponse of(Long commentId) {
		return CommentPersistResponse.builder()
			.commentId(commentId)
			.build();
	}
}
