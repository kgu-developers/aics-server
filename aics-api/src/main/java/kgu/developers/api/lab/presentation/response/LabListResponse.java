package kgu.developers.api.lab.presentation.response;

import java.util.List;

import io.swagger.v3.oas.annotations.media.Schema;
import kgu.developers.domain.lab.domain.Lab;
import lombok.Builder;

@Builder
public record LabListResponse(
	@Schema(description = "등록된 연구실 리스트",
		example = "[{"
			+ "\"name\": nninjo_on Systems Lab, "
			+ "\"loc\": \"1022\", "
			+ "\"site\": \"http://nninjo_on.kyonggi.ac.kr\"}]",
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
