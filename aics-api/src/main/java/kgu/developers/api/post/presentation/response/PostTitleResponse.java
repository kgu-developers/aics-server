package kgu.developers.api.post.presentation.response;

import io.swagger.v3.oas.annotations.media.Schema;
import kgu.developers.domain.post.domain.Post;
import lombok.Builder;

@Builder
public record PostTitleResponse(
	@Schema(description = "게시글 id", example = "1", nullable = true)
	Long postId,

	@Schema(description = "게시글 제목", example = "SW 부트캠프 4기 교육생 모집", nullable = true)
	String postTitle
) {
	public static PostTitleResponse from(Post post) {
		if (post == null) {
			return null;
		}

		return PostTitleResponse.builder()
			.postId(post.getId())
			.postTitle(post.getTitle())
			.build();
	}
}
