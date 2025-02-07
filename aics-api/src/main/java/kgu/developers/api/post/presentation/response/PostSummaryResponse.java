package kgu.developers.api.post.presentation.response;

import io.swagger.v3.oas.annotations.media.Schema;
import kgu.developers.domain.post.domain.Post;
import lombok.Builder;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.format.DateTimeFormatter;

import static io.swagger.v3.oas.annotations.media.Schema.RequiredMode.REQUIRED;

@Builder
public record PostSummaryResponse(
	@Schema(description = "게시글 id", example = "1", requiredMode = REQUIRED)
	Long postId,

	@Schema(description = "게시글 카테고리", example = "학과공지", requiredMode = REQUIRED)
	String category,

	@Schema(description = "게시글 제목", example = "SW 부트캠프 4기 교육생 모집", requiredMode = REQUIRED)
	String title,

	@Schema(description = "작성자 이름", example = "홈피관리자", requiredMode = REQUIRED)
	String author,

	@Schema(description = "게시글 내용 앞부분 30자", example = "2024학년도 학과 소개가 아래와 같은 일정으로 진행됩", requiredMode = REQUIRED)
	String description,

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

		String content = post.getContent();
		String description = content.length() > 80 ? content.substring(0, 80) : content;

		return PostSummaryResponse.builder()
			.postId(post.getId())
			.category(post.getCategory().getDescription())
			.title(post.getTitle())
			.author(post.getAuthor().getName())
			.description(description)
			.views(post.getViews())
			.hasAttachment(false) // TODO : 첨부파일 여부 확인
			.isPinned(post.isPinned())
			.createdAt(post.getCreatedAt().format(formatter))
			.build();
	}
}
