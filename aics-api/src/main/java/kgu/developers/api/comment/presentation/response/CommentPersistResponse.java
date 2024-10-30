package kgu.developers.api.comment.presentation.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;

import static io.swagger.v3.oas.annotations.media.Schema.RequiredMode.REQUIRED;

@Builder
public record CommentPersistResponse(
	@Schema(description = "댓글 ID", example = "1122", requiredMode = REQUIRED)
	Long commentId
) {
	public static CommentPersistResponse of(Long commentId) {
		return CommentPersistResponse.builder()
			.commentId(commentId)
			.build();
	}
}
