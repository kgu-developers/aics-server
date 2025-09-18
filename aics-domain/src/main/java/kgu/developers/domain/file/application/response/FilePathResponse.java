package kgu.developers.domain.file.application.response;

import static io.swagger.v3.oas.annotations.media.Schema.RequiredMode.REQUIRED;

import io.swagger.v3.oas.annotations.media.Schema;
import kgu.developers.domain.file.domain.FileModel;
import kgu.developers.globalutils.encryption.AesUtil;
import lombok.Builder;

@Builder
public record FilePathResponse(
	@Schema(description = "파일 id", example = "1", requiredMode = REQUIRED)
	Long id,

	@Schema(description = "파일 경로", example = "/files/2025-curriculum", requiredMode = REQUIRED)
	String physicalPath
) {
	public static FilePathResponse from(FileModel fileEntity) {
		String decryptedPath = AesUtil.decrypt(fileEntity.getPhysicalPath());
		return FilePathResponse.builder()
			.id(fileEntity.getId())
			.physicalPath(decryptedPath)
			.build();
	}

	public static FilePathResponse of(Long id, String physicalPath) {
		String decryptedPath = AesUtil.decrypt(physicalPath);
		return FilePathResponse.builder()
			.id(id)
			.physicalPath(decryptedPath)
			.build();
	}
}