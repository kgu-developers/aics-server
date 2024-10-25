package kgu.developers.apis.api.post.presentation.response;

import static io.swagger.v3.oas.annotations.media.Schema.RequiredMode.*;

import java.util.List;

import io.swagger.v3.oas.annotations.media.Schema;
import kgu.developers.core.common.response.PageableResponse;
import lombok.Builder;

@Builder
public record PostPageResponse<T>(
	@Schema(description = "게시글 정보 리스트",
		example = "[{\n\"postId\": 1023,\n\""
			+ "postTitle\": \"민준이의 비밀은?\",\n\""
			+ "author\": \"nninjo_on\",\n\""
			+ "date\": \"1999-10-22\",\n\""
			+ "views\": 1999\",\n\""
			+ "hasAttachment\": true\n},"
			+ "\n{\n\"postId\": 1022,\n\""
			+ "postTitle\": \"KGU DEVELOPERS 화이팅\",\n\""
			+ "author\": \"zi존민준짱짱123\",\n\""
			+ "date\": \"1999-10-22\",\n\""
			+ "views\": 1999\",\n\""
			+ "hasAttachment\": false\n}]",
		requiredMode = REQUIRED)
	List<PostInfoResponse> contents,

	@Schema(description = "페이징 정보", requiredMode = REQUIRED)
	PageableResponse<T> pageable
) {
	public static <T> PostPageResponse<T> of(List<PostInfoResponse> contents, PageableResponse<T> pageable) {
		return PostPageResponse.<T>builder()
			.contents(contents)
			.pageable(pageable)
			.build();
	}
}
