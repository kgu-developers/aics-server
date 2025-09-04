package kgu.developers.api.lab.presentation.response;

import static io.swagger.v3.oas.annotations.media.Schema.RequiredMode.REQUIRED;

import java.util.List;
import java.util.Map;

import io.swagger.v3.oas.annotations.media.Schema;
import kgu.developers.domain.lab.domain.Lab;
import lombok.Builder;

@Builder
public record LabListResponse(
	@Schema(description = "등록된 연구실 리스트",
		example = "[{"
			+ "\"id\": 1, "
			+ "\"name\": \"인공지능연구실\", "
			+ "\"loc\": \"8502, 8503\", "
			+ "\"site\": \"http://ailab.kyonggi.ac.kr\", "
			+ "\"professor\": \"박민준\", "
			+ "\"img\": {\"id\": 1, \"physicalPath\": \"/files/lab/20250131/lab-A.png\"}"
			+ "}]",
		requiredMode = REQUIRED)
	List<LabDetailResponse> contents
) {
	public static LabListResponse from(List<Lab> labs, Map<Long, String> fileMap) {
		return LabListResponse.builder()
			.contents(labs.stream()
				.map(lab -> LabDetailResponse.from(lab,fileMap.get(lab.getFileId())))
				.toList())
			.build();
	}
}
