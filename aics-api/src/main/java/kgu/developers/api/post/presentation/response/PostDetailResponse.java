package kgu.developers.api.post.presentation.response;

import static io.swagger.v3.oas.annotations.media.Schema.RequiredMode.REQUIRED;

import java.time.format.DateTimeFormatter;

import org.springframework.format.annotation.DateTimeFormat;

import io.swagger.v3.oas.annotations.media.Schema;
import kgu.developers.api.file.presentation.response.FileResponse;
import kgu.developers.domain.post.domain.Post;
import lombok.Builder;

@Builder
public record PostDetailResponse(
	@Schema(description = "게시글 id", example = "1", requiredMode = REQUIRED)
	Long postId,

	@Schema(description = "게시글 카테고리", example = "학과공지", requiredMode = REQUIRED)
	String category,

	@Schema(description = "게시글 제목", example = "SW 부트캠프 4기 교육생 모집", requiredMode = REQUIRED)
	String title,

	@Schema(description = "내용",
		example = """
			SW 부트캠프 4기 교육생 모집
			SW전문인재양성사업단에서는 SW부트캠프 4기 교육생을 모집합니다.
			관심있는 학생들의 많은 신청 바랍니다.
			- 모집기간 : 2024.10.07(월)~10.15(화)
			- 신청방법 :https://kebkyonggi.quv.kr/68
			""",
		requiredMode = REQUIRED)
	String content,

	@Schema(description = "작성자 이름", example = "홈피관리자", requiredMode = REQUIRED)
	String author,

	@Schema(description = "조회수", example = "19", requiredMode = REQUIRED)
	int views,

	@Schema(description = "상단 고정 여부", example = "false", requiredMode = REQUIRED)
	boolean isPinned,

	@Schema(description = "게시글에 첨부된 파일",
		example = "{\"logicalName\": \"사용자가 업로드 한 파일 이름.png\", "
			+ "\"physicalPath\": \"upload/도메인명/yy/MM/dd/유니크이름.png\"}",
		requiredMode = Schema.RequiredMode.REQUIRED)
	FileResponse file,

	@Schema(description = "작성일", example = "2024-11-11 15:45", requiredMode = REQUIRED)
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm")
	String createdAt

) {
	public static PostDetailResponse from(Post post) {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
		return PostDetailResponse.builder()
			.postId(post.getId())
			.category(post.getCategory().getDescription())
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
