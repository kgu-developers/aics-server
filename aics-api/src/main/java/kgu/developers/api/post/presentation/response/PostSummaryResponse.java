package kgu.developers.api.post.presentation.response;

import static io.swagger.v3.oas.annotations.media.Schema.RequiredMode.*;

import java.time.format.DateTimeFormatter;

import org.springframework.format.annotation.DateTimeFormat;

import io.swagger.v3.oas.annotations.media.Schema;
import kgu.developers.domain.post.domain.Post;
import lombok.Builder;

@Builder
public record PostSummaryResponse(
	@Schema(description = "게시글 ID", example = "1022", requiredMode = REQUIRED)
	Long postId,

	@Schema(description = "게시글 제목", example = "KGU DEVELOPERS 화이팅", requiredMode = REQUIRED)
	String title,

	@Schema(description = "작성자 이름", example = "zi존민준짱짱123", requiredMode = REQUIRED)
	String author,

	@Schema(description = "조회수", example = "19", requiredMode = REQUIRED)
	int views,

	@Schema(description = "첨부파일 여부", example = "false", requiredMode = REQUIRED)
	boolean hasAttachment,

	@Schema(description = "작성일", example = "1999-10-22", requiredMode = REQUIRED)
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	String createdAt
) {
	public static PostSummaryResponse from(Post post) {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		return PostSummaryResponse.builder()
			.postId(post.getId())
			.title(post.getTitle())
			.author(post.getAuthor().getName())
			.views(post.getViews())
			.hasAttachment(false) // TODO : 첨부파일 여부 확인
			.createdAt(post.getCreatedAt().format(formatter))
			.build();
	}
}
