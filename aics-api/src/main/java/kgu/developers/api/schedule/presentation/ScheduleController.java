package kgu.developers.api.schedule.presentation;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import kgu.developers.api.schedule.presentation.response.ScheduleListResponse;
import kgu.developers.api.schedule.presentation.response.ScheduleSummaryResponse;
import kgu.developers.api.schedule.presentation.response.ScheduleTypeContentResponse;
import kgu.developers.domain.schedule.domain.SubmissionType;
import org.springframework.http.ResponseEntity;

@Tag(name = "Schedule", description = "일정관리 API")
public interface ScheduleController {

    @Operation(summary = "전체 일정 조회 API", description = """
        - Description : 이 API는 모든 일정을 조회합니다.
        - Assignee : 주윤빈
    """)
    @ApiResponse(
            responseCode = "200",
            content = @Content(schema = @Schema(implementation = ScheduleListResponse.class)))
    ResponseEntity<ScheduleListResponse> getScheduleList();

    @Operation(summary = "유형별 일정 본문 조회 API", description = """
        - Description : 이 API는 제출유형별 본문만 조회합니다.
        - Assignee : 주윤빈
    """)
    @ApiResponse(
            responseCode = "200",
            content = @Content(schema = @Schema(implementation = ScheduleTypeContentResponse.class)))
    ResponseEntity<ScheduleTypeContentResponse> getSchedulesByType(SubmissionType type);

    @Operation(summary = "단일 일정 조회 API", description = """
        - Description : 이 API는 단일 일정을 조회합니다.
        - Assignee : 주윤빈
    """)
    @ApiResponse(
            responseCode = "200",
            content = @Content(schema = @Schema(implementation = ScheduleSummaryResponse.class)))
    ResponseEntity<ScheduleSummaryResponse> getScheduleById(Long id);
}
