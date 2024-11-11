package kgu.developers.api.lab.presentation.response;

import io.swagger.v3.oas.annotations.media.Schema;
import kgu.developers.domain.lab.domain.Lab;
import lombok.Builder;

import java.util.List;

@Builder
public record LabListResponse(
	@Schema(description = "등록된 연구실 리스트",
		example = "[{"
			+ "\"name\": 인공지능연구실, "
			+ "\"loc\": \"8502, 8503\", "
			+ "\"site\": \"http://ailab.kyonggi.ac.kr\"}]",
		requiredMode = Schema.RequiredMode.REQUIRED)
	List<LabDetailResponse> contents
) {
	public static LabListResponse from(List<Lab> labs) {
		return LabListResponse.builder()
			.contents(labs.stream()
				.map(LabDetailResponse::from)
				.toList())
			.build();
	}
}
