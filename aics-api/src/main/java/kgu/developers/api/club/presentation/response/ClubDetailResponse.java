package kgu.developers.api.club.presentation.response;

import static io.swagger.v3.oas.annotations.media.Schema.RequiredMode.NOT_REQUIRED;
import static io.swagger.v3.oas.annotations.media.Schema.RequiredMode.REQUIRED;

import io.swagger.v3.oas.annotations.media.Schema;
import kgu.developers.domain.club.domain.Club;
import kgu.developers.domain.file.application.response.FilePathResponse;
import kgu.developers.domain.file.domain.FileEntity;
import lombok.Builder;

@Builder
public record ClubDetailResponse(
	@Schema(description = "동아리 id", example = "1", requiredMode = REQUIRED)
	Long id,

	@Schema(description = "동아리 이름", example = "C-Lab", requiredMode = REQUIRED)
	String name,

	@Schema(description = "동아리 설명", example = "경기대학교 AI컴퓨터공학부 개발동아리입니다.", requiredMode = REQUIRED)
	String description,

	@Schema(description = "동아리 사이트", example = "https://www.clab.page/", requiredMode = NOT_REQUIRED)
	String site,

	@Schema(description = "첨부 파일 정보",
		example = "{\"physicalPath\": \"/cloud/file/3/2025-curriculum\"}",
		requiredMode = NOT_REQUIRED)
	FilePathResponse file
) {
	public static ClubDetailResponse from(Club club, String physicalPath) {
		return ClubDetailResponse.builder()
			.id(club.getId())
			.name(club.getName())
			.description(club.getDescription())
			.site(club.getSite())
			.file(club.getFileId() != null ? FilePathResponse.of(club.getFileId(), physicalPath) : null)
			.build();
	}
}
