package kgu.developers.api.post.presentation.response;

import static io.swagger.v3.oas.annotations.media.Schema.RequiredMode.REQUIRED;

import io.swagger.v3.oas.annotations.media.Schema;
import kgu.developers.domain.post.domain.Post;
import lombok.Builder;

@Builder
public record PostTitleResponse(
	@Schema(description = "게시글 id", example = "1", requiredMode = REQUIRED)
	Long postId,

	@Schema(description = "게시글 제목", example = "SW 부트캠프 4기 교육생 모집", requiredMode = REQUIRED)
	String title
) {
	public static PostTitleResponse from(Post post) {
		if (post == null) {
			return null;
		}

		return PostTitleResponse.builder()
			.postId(post.getId())
			.title(post.getTitle())
			.build();
	}
}
