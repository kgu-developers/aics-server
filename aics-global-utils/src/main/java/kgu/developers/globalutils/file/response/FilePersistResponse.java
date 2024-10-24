package kgu.developers.globalutils.file.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;

import static io.swagger.v3.oas.annotations.media.Schema.RequiredMode.REQUIRED;

// 해당 DTO를 api 아래에 둘 경우 FileHandler에서 접근하려면 순환참조 발생
@Builder
public record FilePersistResponse (
	@Schema(description = "파일ID", example = "1", requiredMode = REQUIRED)
	String id
) {
}
