package kgu.developers.api.comment.presentation.response;

import static io.swagger.v3.oas.annotations.media.Schema.RequiredMode.REQUIRED;

import java.util.List;

import io.swagger.v3.oas.annotations.media.Schema;
import kgu.developers.domain.comment.Comment;
import lombok.Builder;

@Builder
public record CommentListResponse(
	@Schema(description = "게시글에 첨부된 파일",
		example = "[{"
			+ "\"commentId\": 1122, "
			+ "\"author\": \"이신행\", "
			+ "\"createdAt\": \"1999-10-22\", "
			+ "\"content\": \"예시 코멘트 입니다~~\"}]",
		requiredMode = REQUIRED)
	List<CommentResponse> contents
) {
	public static CommentListResponse from(List<Comment> comments) {
		return CommentListResponse.builder()
			.contents(comments.stream()
				.map(CommentResponse::from)
				.toList())
			.build();
	}
}
