package kgu.developers.apis.api.post.presentation.response;

import static io.swagger.v3.oas.annotations.media.Schema.RequiredMode.*;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;

@Builder
public record PostPersistResponse(
	@Schema(description = "게시글 id", example = "19991022", requiredMode = REQUIRED)
	Long postId
) {
	public static PostPersistResponse from(Long postId) {
		return PostPersistResponse.builder()
			.postId(postId)
			.build();
	}
}
