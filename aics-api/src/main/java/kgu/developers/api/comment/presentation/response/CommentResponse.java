package kgu.developers.api.comment.presentation.response;

import static io.swagger.v3.oas.annotations.media.Schema.RequiredMode.REQUIRED;

import io.swagger.v3.oas.annotations.media.Schema;
import kgu.developers.domain.comment.domain.Comment;
import lombok.Builder;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.format.DateTimeFormatter;

@Builder
public record CommentResponse(
	@Schema(description = "댓글 id", example = "1", requiredMode = REQUIRED)
	Long commentId,

	@Schema(description = "작성자 이름", example = "이신행", requiredMode = REQUIRED)
	String author,

	@Schema(description = "작성일", example = "2024-11-11", requiredMode = REQUIRED)
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm")
	String createdAt,

	@Schema(description = "내용", example = "예시 코멘트입니다, 좋은 소식이네요!", requiredMode = REQUIRED)
	String content
) {
	public static CommentResponse from(Comment comment) {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
		return CommentResponse.builder()
			.commentId(comment.getId())
			.author(comment.getAuthor().getName())
			.createdAt(comment.getCreatedAt().format(formatter))
			.content(comment.getContent())
			.build();
	}
}
