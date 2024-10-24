package kgu.developers.apis.api.post.presentation.response;

import static io.swagger.v3.oas.annotations.media.Schema.RequiredMode.*;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import kgu.developers.core.domain.post.Post;

public record PostCreateResponse(
	@Schema(description = "게시글 id", example = "19991022", requiredMode = REQUIRED)
	@NotNull(message = "게시글이 저장되지 않았습니다.")
	Long postId

	//추가해야 될 변수 있으면 말씀해주세용
) {
	public static PostCreateResponse from(Post post) {
		return new PostCreateResponse(post.getId());
	}
}
