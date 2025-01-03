package kgu.developers.api.club.presentation.response;

import java.util.List;

import io.swagger.v3.oas.annotations.media.Schema;
import kgu.developers.domain.club.domain.Club;
import lombok.Builder;

@Builder
public record ClubListResponse(
	@Schema(description = "등록된 동아리 리스트",
		example = "[{"
			+ "\"name\": C-Lab, "
			+ "\"description\": \"경기대학교 AI컴퓨터공학부 개발동아리입니다.\", "
			+ "\"site\": \"https://www.clab.page/\"}]",
		requiredMode = Schema.RequiredMode.REQUIRED)
	List<ClubDetailResponse> contents
) {
	public static ClubListResponse from(List<Club> clubs) {
		return ClubListResponse.builder()
			.contents(clubs.stream()
				.map(ClubDetailResponse::from)
				.toList())
			.build();
	}
}

