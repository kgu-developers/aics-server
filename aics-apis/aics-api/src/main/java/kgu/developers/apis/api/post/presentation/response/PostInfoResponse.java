package kgu.developers.apis.api.post.presentation.response;

import static io.swagger.v3.oas.annotations.media.Schema.RequiredMode.*;

import java.time.LocalDate;

import io.swagger.v3.oas.annotations.media.Schema;
import kgu.developers.core.domain.post.Post;
import lombok.Builder;

@Builder
public record PostInfoResponse(
	@Schema(description = "게시글 ID", example = "1022", requiredMode = REQUIRED)
	Long postId,

	@Schema(description = "게시글 제목", example = "KGU DEVELOPERS 화이팅", requiredMode = REQUIRED)
	String postTitle,

	@Schema(description = "작성자 이름", example = "zi존민준짱짱123", requiredMode = REQUIRED)
	String authorName,

	@Schema(description = "작성일", example = "1999-10-22", requiredMode = REQUIRED)
	LocalDate createDate,

	@Schema(description = "조회수", example = "19", requiredMode = REQUIRED)
	int views,

	@Schema(description = "첨부파일 여부", example = "false", requiredMode = REQUIRED)
	boolean hasAttachment
) {
	public static PostInfoResponse from(Post post) {
		return PostInfoResponse.builder()
			.postId(post.getId())
			.postTitle(post.getTitle())
			.authorName(post.getAuthor().getName())
			.createDate(post.getCreatedAt().toLocalDate())
			.views(post.getViews())
			//		.hasAttachment(post.hasAttachment())
			.build();
	}
}
