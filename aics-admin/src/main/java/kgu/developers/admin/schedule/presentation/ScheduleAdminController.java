package kgu.developers.admin.schedule.presentation;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Positive;
import kgu.developers.admin.schedule.presentation.request.ScheduleContentUpdateRequest;
import kgu.developers.admin.schedule.presentation.request.ScheduleCreateRequest;
import kgu.developers.admin.schedule.presentation.request.ScheduleUpdateRequest;
import kgu.developers.admin.schedule.presentation.response.SchedulePersistResponse;
import kgu.developers.domain.schedule.domain.SubmissionType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;


@Tag(name = "Schedule", description = "일정 관리 API")
public interface ScheduleAdminController {

    @Operation(summary = "일정 생성 API", description = """
		    - Description : 이 API는 일정을 생성합니다.
		    - Assignee : 주윤빈
		""")
    @ApiResponse(
            responseCode = "201",
            content = @Content(schema = @Schema(implementation = SchedulePersistResponse.class)))
	ResponseEntity<SchedulePersistResponse> createSchedule(
			@Parameter(
					description = "일정 생성 request 객체 입니다.",
					required = true
			)@Valid @RequestBody
			ScheduleCreateRequest request
	);

	@Operation(summary = "일정 수정 API", description = """
		    - Description : 이 API는 일정을 수정합니다.
		    - Assignee : 주윤빈
		""")
	@ApiResponse(responseCode = "204")
	ResponseEntity<Void> updateSchedule(
			@Parameter(
					description = "이 API는 제출 유형/제목/기간을 수정합니다.",
					example = "1",
					required = true
			)@Positive @PathVariable Long scheduleId,
			@Parameter(
					description = "일정 수정 request 객체 입니다.",
					required = true
			)@Valid @RequestBody ScheduleUpdateRequest request
	);

	@Operation(summary = "유형별 본문 수정 API",description = """
			-Description : 이 API는 제출 유형을 기준으로 본문만 수정합니다.
			-Assignee : 주윤빈
			""")
	@ApiResponse(responseCode = "204")
	ResponseEntity<Void> updateScheduleContent(
			@Parameter(
					description = "본문을 수정할 제출 유형입니다.",
					example = "MIDTHESIS",
					required = true
			)@PathVariable SubmissionType submissionType,
			@Parameter(description = "본문 수정 request 객체 입니다.",
					required = true
			)@Valid @RequestBody ScheduleContentUpdateRequest request
	);

	@Operation(summary = "일정 삭제 API", description = """
		    - Description : 이 API는 일정을 삭제합니다.
		    - Assignee : 주윤빈
		""")
	@ApiResponse(responseCode = "204")
	ResponseEntity<Void> deleteSchedule(
			@Parameter(
					description = "삭제할 일정의 ID 입니다.",
					example = "1",
					required = true
			)@Positive @PathVariable Long scheduleId
	);
}
