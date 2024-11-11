package kgu.developers.api.file.presentation.response;

import static io.swagger.v3.oas.annotations.media.Schema.RequiredMode.REQUIRED;

import io.swagger.v3.oas.annotations.media.Schema;
import kgu.developers.domain.file.domain.FileEntity;
import lombok.Builder;

@Builder
public record FileResponse(
	@Schema(description = "업로드 때 사용한 파일명", example = "사용자가 업로드 한 파일 이름.png", requiredMode = REQUIRED)
	String logicalName,

	@Schema(description = "저장할 때 사용한 파일명", example = "upload/도메인명/yy/MM/dd/유니크이름.png", requiredMode = REQUIRED)
	String physicalPath
) {
	public static FileResponse from(FileEntity file) {
		return FileResponse.builder()
			.logicalName(file.getLogicalName())
			.physicalPath(file.getPhysicalPath())
			.build();
	}
}
