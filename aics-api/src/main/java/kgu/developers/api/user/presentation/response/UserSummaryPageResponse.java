package kgu.developers.api.user.presentation.response;

import static io.swagger.v3.oas.annotations.media.Schema.RequiredMode.REQUIRED;

import java.util.List;

import io.swagger.v3.oas.annotations.media.Schema;
import kgu.developers.common.response.PageableResponse;
import kgu.developers.domain.user.domain.User;
import lombok.Builder;

@Builder
public record UserSummaryPageResponse<T>(
	@Schema(description = "유저 정보 리스트",
		example = "[{"
			+ "\"id\": 201912065, "
			+ "\"name\": \"nninjo_on\", "
			+ "\"major\": \"컴퓨터공학부\"}]",
		requiredMode = REQUIRED)
	List<UserSummaryResponse> contents,

	@Schema(description = "페이징 정보", requiredMode = REQUIRED)
	PageableResponse<T> pageable
) {
	public static <T> UserSummaryPageResponse<T> of(List<User> users, PageableResponse<T> pageable) {
		return UserSummaryPageResponse.<T>builder()
			.contents(users.stream()
				.map(UserSummaryResponse::from)
				.toList())
			.pageable(pageable)
			.build();
	}
}
