package kgu.developers.api.user.presentation.response;

import static io.swagger.v3.oas.annotations.media.Schema.RequiredMode.REQUIRED;

import io.swagger.v3.oas.annotations.media.Schema;
import kgu.developers.domain.user.domain.Major;
import kgu.developers.domain.user.domain.User;
import lombok.Builder;

@Builder
public record UserSummaryResponse(
	@Schema(description = "학번(교번)", example = "201912345", requiredMode = REQUIRED)
	String id,

	@Schema(description = "이름", example = "홍길동", requiredMode = REQUIRED)
	String name,

	@Schema(description = "학과", example = "CSE", requiredMode = REQUIRED)
	Major major
) {
	public static UserSummaryResponse from(User user) {
		return UserSummaryResponse.builder()
			.id(user.getId())
			.name(user.getName())
			.major(user.getMajor())
			.build();
	}
}
