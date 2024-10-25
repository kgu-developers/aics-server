package kgu.developers.apis.api.post.presentation.response;

import static io.swagger.v3.oas.annotations.media.Schema.RequiredMode.*;

import io.swagger.v3.oas.annotations.media.Schema;
import kgu.developers.core.domain.post.Post;

public record PostPersistResponse(
	@Schema(description = "게시글 id", example = "19991022", requiredMode = REQUIRED)
	Long postId
) {
	public static PostPersistResponse from(Post post) {
		return new PostPersistResponse(post.getId());
	}
}
