package kgu.developers.api.comment.presentation.response;

import static io.swagger.v3.oas.annotations.media.Schema.RequiredMode.REQUIRED;

import io.swagger.v3.oas.annotations.media.Schema;
import kgu.developers.domain.comment.domain.Comment;
import lombok.Builder;

import java.util.List;

@Builder
public record CommentListResponse(
	@Schema(description = "게시글에 첨부된 파일",
		example = "[{"
			+ "\"commentId\": 1, "
			+ "\"author\": \"이신행\", "
			+ "\"createdAt\": \"2024-11-11\", "
			+ "\"content\": \"예시 코멘트입니다, 좋은 소식이네요!\"}]",
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
