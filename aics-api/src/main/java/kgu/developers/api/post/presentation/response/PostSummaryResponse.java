package kgu.developers.api.post.presentation.response;

import static io.swagger.v3.oas.annotations.media.Schema.RequiredMode.REQUIRED;

import io.swagger.v3.oas.annotations.media.Schema;
import kgu.developers.domain.post.domain.Post;
import lombok.Builder;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.format.DateTimeFormatter;

@Builder
public record PostSummaryResponse(
	@Schema(description = "게시글 id", example = "1", requiredMode = REQUIRED)
	Long postId,

	@Schema(description = "게시글 제목", example = "SW 부트캠프 4기 교육생 모집", requiredMode = REQUIRED)
	String title,

	@Schema(description = "작성자 이름", example = "홈피관리자", requiredMode = REQUIRED)
	String author,

	@Schema(description = "조회수", example = "19", requiredMode = REQUIRED)
	int views,

	@Schema(description = "첨부파일 여부", example = "false", requiredMode = REQUIRED)
	boolean hasAttachment,

	@Schema(description = "상단 고정 여부", example = "false", requiredMode = REQUIRED)
	boolean isPinned,

	@Schema(description = "작성일", example = "2024-11-11", requiredMode = REQUIRED)
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
			.isPinned(post.isPinned())
			.createdAt(post.getCreatedAt().format(formatter))
			.build();
	}
}
