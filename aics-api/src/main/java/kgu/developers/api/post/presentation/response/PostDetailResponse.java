package kgu.developers.api.post.presentation.response;

import io.swagger.v3.oas.annotations.media.Schema;
import kgu.developers.domain.post.domain.Post;
import lombok.Builder;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.format.DateTimeFormatter;
import java.util.List;

import static io.swagger.v3.oas.annotations.media.Schema.RequiredMode.REQUIRED;

@Builder
public record PostDetailResponse(
	@Schema(description = "게시글 ID", example = "1122", requiredMode = REQUIRED)
	Long postId,

	@Schema(description = "게시글 제목", example = "KGU DEVELOPERS 화이팅", requiredMode = REQUIRED)
	String title,

	@Schema(description = "작성자 이름", example = "이신행", requiredMode = REQUIRED)
	String author,

	@Schema(description = "조회수", example = "19", requiredMode = REQUIRED)
	int views,

	@Schema(description = "작성일", example = "1999-10-22", requiredMode = REQUIRED)
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm")
	String createdAt,

	@Schema(description = "내용", example = "예시 내용 뭐로 할까? \n 모르겠다 ", requiredMode = REQUIRED)
	String content,

	@Schema(description = "게시글에 첨부된 파일",
		example = "[{"
			+ "\"commentId\": 1122, "
			+ "\"author\": \"이신행\", "
			+ "\"createdAt\": \"1999-10-22\", "
			+ "\"content\": \"예시 코멘트 입니다~~\"}]",
		requiredMode = REQUIRED)
	List<CommentPostDetailResponse> comments
/*
	// TODO 파일 붙이고 나서 주석 해제
	@Schema(description = "게시글에 첨부된 파일",
		example = "[{"
			+ "\"logicalName\": \"사용자가 업로드 한 파일 이름.png\", "
			+ "\"physicalPath\": \"upload/도메인명/yy/MM/dd/유니크이름.png\"]",
		requiredMode = REQUIRED)
	FileResponse file
*/
) {
	public static PostDetailResponse from(Post post) {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
		return PostDetailResponse.builder()
			.postId(post.getId())
			.title(post.getTitle())
			.author(post.getAuthor().getName())
			.views(post.getViews())
			.createdAt(post.getCreatedAt().format(formatter))
			.content(post.getContent())
			.comments(
				post.getComments().stream()
					.map(CommentPostDetailResponse::from)
					.toList()
			)
//			.file(FilePostDetailResponse.from(post.getAttachment()))
			.build();
	}
}
