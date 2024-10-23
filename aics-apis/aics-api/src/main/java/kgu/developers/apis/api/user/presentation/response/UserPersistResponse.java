package kgu.developers.apis.api.user.presentation.response;

import io.swagger.v3.oas.annotations.media.Schema;

import static io.swagger.v3.oas.annotations.media.Schema.RequiredMode.REQUIRED;

public record UserPersistResponse(
	@Schema(description = "유저 ID", example = "202412345", requiredMode = REQUIRED)
	String id
) {
	public static UserPersistResponse of(String id) {
		return new UserPersistResponse(id);
	}
}
