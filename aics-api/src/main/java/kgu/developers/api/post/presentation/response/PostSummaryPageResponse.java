package kgu.developers.api.post.presentation.response;

import static io.swagger.v3.oas.annotations.media.Schema.RequiredMode.REQUIRED;

import io.swagger.v3.oas.annotations.media.Schema;
import kgu.developers.common.response.PageableResponse;
import kgu.developers.domain.post.domain.Post;
import lombok.Builder;

import java.util.List;

@Builder
public record PostSummaryPageResponse<T>(
	@Schema(description = "게시글 정보 리스트",
		example = "[{"
			+ "\"postId\": 3, "
			+ "\"title\": \"SW 부트캠프 4기 교육생 모집\", "
			+ "\"author\": \"홈피관리자\", "
			+ "\"views\": 19, "
			+ "\"hasAttachment\": false, "
			+ "\"isPinned\": false, "
			+ "\"createdAt\": \"2024-11-11\"}]",
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
