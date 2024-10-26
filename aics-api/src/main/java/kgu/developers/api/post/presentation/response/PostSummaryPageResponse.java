package kgu.developers.api.post.presentation.response;

import static io.swagger.v3.oas.annotations.media.Schema.RequiredMode.REQUIRED;

import java.util.List;

import io.swagger.v3.oas.annotations.media.Schema;
import kgu.developers.common.response.PageableResponse;
import kgu.developers.domain.post.domain.Post;
import lombok.Builder;

@Builder
public record PostSummaryPageResponse<T>(
	@Schema(description = "게시글 정보 리스트",
		example = "[{"
			+ "\"postId\": 1022, "
			+ "\"title\": \"KGU DEVELOPERS 화이팅\", "
			+ "\"author\": \"zi존민준짱짱123\", "
			+ "\"views\": 19, "
			+ "\"hasAttachment\": false, "
			+ "\"createdAt\": \"1999-10-22\"}]",
		requiredMode = REQUIRED)
	List<PostSummaryResponse> contents,

	@Schema(description = "페이징 정보", requiredMode = REQUIRED)
	PageableResponse<T> pageable
) {
	public static <T> PostSummaryPageResponse<T> of(List<Post> posts, PageableResponse<T> pageable) {
		return PostSummaryPageResponse.<T>builder()
			.contents(posts.stream()
				.map(PostSummaryResponse::from)
				.toList())
			.pageable(pageable)
			.build();
	}
}
