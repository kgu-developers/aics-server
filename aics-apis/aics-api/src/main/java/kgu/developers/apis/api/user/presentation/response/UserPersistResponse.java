package kgu.developers.apis.api.user.presentation.response;

import static io.swagger.v3.oas.annotations.media.Schema.RequiredMode.REQUIRED;
import io.swagger.v3.oas.annotations.media.Schema;

public record UserPersistResponse(
	@Schema(description = "유저 ID", example = "1", requiredMode = REQUIRED)
	Long id
) {
	public static UserPersistResponse of(Long id) {
		return new UserPersistResponse(id);
	}
}
