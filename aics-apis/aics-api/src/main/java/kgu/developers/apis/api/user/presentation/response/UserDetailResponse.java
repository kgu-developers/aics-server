package kgu.developers.apis.api.user.presentation.response;

import io.swagger.v3.oas.annotations.media.Schema;
import kgu.developers.core.domain.user.domain.Major;
import kgu.developers.core.domain.user.domain.Role;
import kgu.developers.core.domain.user.domain.User;
import lombok.Builder;

import static io.swagger.v3.oas.annotations.media.Schema.RequiredMode.REQUIRED;

@Builder
public record UserDetailResponse(
	@Schema(description = "이름", example = "홍길동", requiredMode = REQUIRED)
	String name,

	@Schema(description = "전화번호", example = "010-1234-5678", requiredMode = REQUIRED)
	String phoneNumber,

	@Schema(description = "이메일", example = "example@gmail.com", requiredMode = REQUIRED)
	String email,

	@Schema(description = "구분", example = "학부생", requiredMode = REQUIRED)
	Role role,

	@Schema(description = "학과", example = "컴퓨터공학과", requiredMode = REQUIRED)
	Major major,

	@Schema(description = "학번(교번)", example = "201912345", requiredMode = REQUIRED)
	String userId
) {
	public static UserDetailResponse from(
		User user
	) {
		return UserDetailResponse.builder()
			.name(user.getName())
			.phoneNumber(user.getPhoneNumber())
			.email(user.getEmail())
			.role(user.getRole())
			.major(user.getMajor())
			.userId(user.getUserId())
			.build();
	}
}
