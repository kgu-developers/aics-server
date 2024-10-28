package kgu.developers.api.file.presentation.response;

import io.swagger.v3.oas.annotations.media.Schema;
import kgu.developers.domain.file.domain.FileEntity;
import lombok.Builder;

import static io.swagger.v3.oas.annotations.media.Schema.RequiredMode.REQUIRED;

@Builder
public record FilePostDetailResponse(
	@Schema(description = "업로드 때 사용한 파일명", example = "사용자가 업로드 한 파일 이름.png", requiredMode = REQUIRED)
	String logicalName,

	@Schema(description = "저장할 때 사용한 파일명", example = "저장된/경로와/저장된/유니크이름.png", requiredMode = REQUIRED)
	String physicalPath
) {
	public static FilePostDetailResponse from(FileEntity file) {
		return FilePostDetailResponse.builder()
			.logicalName(file.getLogicalName())
			.physicalPath(file.getPhysicalPath())
			.build();
	}
}
