package kgu.developers.admin.graduationUser.presentation.response;

import io.swagger.v3.oas.annotations.media.Schema;

public sealed interface GraduationUserStatusResponse
        permits GraduationUserStatusResponse.Certificate,
        GraduationUserStatusResponse.Thesis {

    @Schema(description = "자격증 제출 상태")
    record Certificate(
            @Schema(description = "졸업 타입", example = "CERTIFICATE")
            String type,

            @Schema(description = "파일 제출 여부", example = "true")
            boolean submitted,

            @Schema(description = "승인 여부", example = "false")
            boolean approval
    ) implements GraduationUserStatusResponse {
    }

    @Schema(description = "논문 제출 상태")
    record Thesis(
            @Schema(description = "졸업 타입", example = "THESIS")
            String type,

            @Schema(description = "중간 논문 상태")
            Middle midThesis,

            @Schema(description = "최종 논문 상태")
            Final finalThesis
    ) implements GraduationUserStatusResponse {

        @Schema(description = "중간 논문 제출 상태")
        public record Middle(
                @Schema(description = "파일 제출 여부", example = "true")
                boolean submitted,

                @Schema(description = "승인 여부", example = "true")
                boolean approval
        ) {}

        @Schema(description = "최종 논문 제출 상태")
        public record Final(
                @Schema(description = "파일 제출 여부", example = "false")
                boolean submitted,

                @Schema(description = "승인 여부", example = "false")
                boolean approval
        ) {}
    }
}