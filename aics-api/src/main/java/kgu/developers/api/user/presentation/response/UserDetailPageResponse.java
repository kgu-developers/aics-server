package kgu.developers.api.user.presentation.response;

import static io.swagger.v3.oas.annotations.media.Schema.RequiredMode.REQUIRED;

import java.util.List;

import io.swagger.v3.oas.annotations.media.Schema;
import kgu.developers.common.response.PageableResponse;
import kgu.developers.domain.user.domain.User;
import lombok.Builder;

@Builder
public record UserDetailPageResponse<T>(
	@Schema(description = "유저 정보 리스트",
		example = "[{"
			+ "\"name\": \"nninjo_on\", "
			+ "\"phone\": \"010-7628-5030\", "
			+ "\"email\": \"alswns11346@kgu.ac.kr\", "
			+ "\"role\": \"학부생\", "
			+ "\"major\": \"컴퓨터공학부\", "
			+ "\"id\": \"201912065\"}]",
		requiredMode = REQUIRED)
	List<UserDetailResponse> contents,

	@Schema(description = "페이징 정보", requiredMode = REQUIRED)
	PageableResponse<T> pageable
) {
	public static <T> UserDetailPageResponse<T> of(List<User> users, PageableResponse<T> pageable) {
		return UserDetailPageResponse.<T>builder()
			.contents(users.stream()
				.map(UserDetailResponse::from)
				.toList())
			.pageable(pageable)
			.build();
	}
}
