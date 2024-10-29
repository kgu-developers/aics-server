package kgu.developers.api.post.presentation.response;

import io.swagger.v3.oas.annotations.media.Schema;
import kgu.developers.domain.comment.Comment;
import lombok.Builder;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.format.DateTimeFormatter;

import static io.swagger.v3.oas.annotations.media.Schema.RequiredMode.REQUIRED;

@Builder
public record CommentPostDetailResponse(
	@Schema(description = "댓글 ID", example = "1122", requiredMode = REQUIRED)
	Long commentId,

	@Schema(description = "작성자 이름", example = "이신행", requiredMode = REQUIRED)
	String author,

	@Schema(description = "작성일", example = "1999-10-22", requiredMode = REQUIRED)
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm")
	String createdAt,

	@Schema(description = "내용", example = "예시 코멘트 입니다~~", requiredMode = REQUIRED)
	String content
) {
	public static CommentPostDetailResponse from(Comment comment) {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
		return CommentPostDetailResponse.builder()
			.commentId(comment.getId())
			.author(comment.getAuthor().getName())
			.createdAt(comment.getCreatedAt().format(formatter))
			.content(comment.getContent())
			.build();
	}
}
