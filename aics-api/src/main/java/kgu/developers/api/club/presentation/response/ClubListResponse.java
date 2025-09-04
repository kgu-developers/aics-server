package kgu.developers.api.club.presentation.response;

import java.util.List;
import java.util.Map;

import io.swagger.v3.oas.annotations.media.Schema;
import kgu.developers.domain.club.domain.Club;
import kgu.developers.domain.file.domain.FileEntity;
import lombok.Builder;

import static io.swagger.v3.oas.annotations.media.Schema.RequiredMode.REQUIRED;

@Builder
public record ClubListResponse(
	@Schema(description = "등록된 동아리 리스트",
		example = "[{"
			+ "\"id\": 1, "
			+ "\"name\": \"C-Lab\", "
			+ "\"description\": \"경기대학교 AI컴퓨터공학부 개발동아리입니다.\", "
			+ "\"site\": \"https://www.clab.page/\""
			+ "}]",
		requiredMode = REQUIRED)
	List<ClubDetailResponse> contents
) {
	public static ClubListResponse from(List<Club> clubs, Map<Long,String> fileMap) {
		return ClubListResponse.builder()
			.contents(clubs.stream()
				.map(club -> ClubDetailResponse.from(club,
						fileMap.get(club.getFileId())))
				.toList())
			.build();
	}
}

