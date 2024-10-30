package kgu.developers.api.post.presentation.response;

import static io.swagger.v3.oas.annotations.media.Schema.RequiredMode.NOT_REQUIRED;
import static io.swagger.v3.oas.annotations.media.Schema.RequiredMode.REQUIRED;

import java.time.format.DateTimeFormatter;

import org.springframework.format.annotation.DateTimeFormat;

import io.swagger.v3.oas.annotations.media.Schema;
import kgu.developers.api.file.presentation.response.FileResponse;
import kgu.developers.domain.post.domain.Post;
import lombok.Builder;

@Builder
public record PostDetailResponse(
	@Schema(description = "게시글 ID", example = "1122", requiredMode = REQUIRED)
	Long postId,

	@Schema(description = "게시글 제목", example = "KGU DEVELOPERS 화이팅", requiredMode = REQUIRED)
	String title,

	@Schema(description = "내용", example = "예시 내용 뭐로 할까? \n 모르겠다 ", requiredMode = REQUIRED)
	String content,

	@Schema(description = "작성자 이름", example = "이신행", requiredMode = REQUIRED)
	String author,

	@Schema(description = "조회수", example = "19", requiredMode = REQUIRED)
	int views,

	@Schema(description = "상단 고정 여부", example = "false", requiredMode = REQUIRED)
	boolean isPinned,

	@Schema(description = "게시글에 첨부된 파일",
		example = "[{"
			+ "\"logicalName\": \"사용자가 업로드 한 파일 이름.png\", "
			+ "\"physicalPath\": \"upload/도메인명/yy/MM/dd/유니크이름.png\"]",
		requiredMode = NOT_REQUIRED)
	FileResponse file,

	@Schema(description = "작성일", example = "1999-10-22", requiredMode = REQUIRED)
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm")
	String createdAt

) {
	public static PostDetailResponse from(Post post) {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
		return PostDetailResponse.builder()
			.postId(post.getId())
			.title(post.getTitle())
			.content(post.getContent())
			.author(post.getAuthor().getName())
			.views(post.getViews())
			.isPinned(post.isPinned())
			.file(post.getFile() != null ? FileResponse.from(post.getFile()) : null)
			.createdAt(post.getCreatedAt().format(formatter))
			.build();
	}
}
