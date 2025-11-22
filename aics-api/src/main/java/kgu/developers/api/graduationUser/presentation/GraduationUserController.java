package kgu.developers.api.graduationUser.presentation;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Positive;
import kgu.developers.api.graduationUser.presentation.request.GraduationTypeUpdateRequest;
import kgu.developers.api.graduationUser.presentation.request.GraduationUserEmailUpdateRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

@Tag(name = "GraduationUser", description = "졸업 대상자 API")
public interface GraduationUserController {
    @Operation(summary = "특정 졸업 대상자 졸업 방식 신청 API", description = """
			- Description : 이 API는 특정 졸업 대상자의 졸업 방식을 택하는 API입니다.
			- Assignee : 장영후
		""")
    @ApiResponse(responseCode = "204")
    ResponseEntity<Void> selectGraduationType(
        @Parameter(
            description = "졸업 대상자 ID는 URL 경로 변수 입니다.",
            example = "1",
            required = true
        ) @Positive @PathVariable Long graduationUserId,
        @Parameter(
            description = "졸업 대상자 request 객체 입니다.",
            required = true
        ) @Valid @RequestBody GraduationTypeUpdateRequest request
    );

    @Operation(summary = "특정 졸업 대상자 이메일 API", description = """
			- Description : 이 API는 특정 졸업 대상자의 이메일을 업데이트하는 API입니다.
			- Assignee : 장영후
		""")
    @ApiResponse(responseCode = "204")
    ResponseEntity<Void> updateGraduationUserEmail(
        @Parameter(
            description = "졸업 대상자 ID는 URL 경로 변수 입니다.",
            example = "1",
            required = true
        ) @Positive @PathVariable Long graduationUserId,
        @Parameter(
            description = "졸업 대상자 request 객체 입니다.",
            required = true
        ) @Valid @RequestBody GraduationUserEmailUpdateRequest request
    );
}
