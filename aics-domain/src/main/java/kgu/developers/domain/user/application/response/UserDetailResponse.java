package kgu.developers.domain.user.application.response;

import static io.swagger.v3.oas.annotations.media.Schema.RequiredMode.*;

import io.swagger.v3.oas.annotations.media.Schema;
import kgu.developers.domain.user.domain.Major;
import kgu.developers.domain.user.domain.Role;
import kgu.developers.domain.user.domain.User;
import lombok.Builder;

@Builder
public record UserDetailResponse(
	@Schema(description = "이름", example = "박민준", requiredMode = REQUIRED)
	String name,

	@Schema(description = "전화번호", example = "010-1234-5678", requiredMode = REQUIRED)
	String phone,

	@Schema(description = "이메일", example = "qkralswnsWkd@kyonggi.ac.kr", requiredMode = REQUIRED)
	String email,

	@Schema(description = "구분", example = "학부생", requiredMode = REQUIRED)
	Role role,

	@Schema(description = "학과", example = "컴퓨터공학과", requiredMode = REQUIRED)
	Major major,

	@Schema(description = "학번(교번)", example = "202412345", requiredMode = REQUIRED)
	String id
) {
	public static UserDetailResponse from(
		User user
	) {
		return UserDetailResponse.builder()
			.name(user.getName())
			.phone(user.getPhone())
			.email(user.getEmail())
			.role(user.getRole())
			.major(user.getMajor())
			.id(user.getId())
			.build();
	}
}
