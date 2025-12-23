package kgu.developers.admin.graduationUser.presentation.response;

import io.swagger.v3.oas.annotations.media.Schema;
import kgu.developers.domain.graduationUser.domain.GraduationType;
import lombok.Builder;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import static io.swagger.v3.oas.annotations.media.Schema.RequiredMode.REQUIRED;

/**
 * 졸업 대상자 상태 응답 인터페이스
 * 다른 클래스에서 대상자 상태 응답을 추가로 구현하는 것을 방지하고자 sealed 키워드를 사용하였습니다.
 * 만약 졸업 방식이 추가된다면 해당 클래스에서 추가로 구현하여 작성해주세요.
 */
public sealed interface GraduationUserStatusResponse
        permits GraduationUserStatusResponse.Certificate,
        GraduationUserStatusResponse.Thesis {

    @Builder
    @Schema(description = "자격증 제출 상태")
    record Certificate(
            @Schema(description = "졸업 타입", example = "CERTIFICATE")
            String type,

            @Schema(description = "파일 제출 여부", example = "true")
            boolean submitted,

            @Schema(description = "승인 여부", example = "false")
            boolean approval,

            @Schema(description = "제출일", example = "2024-08-11", requiredMode = REQUIRED)
            @DateTimeFormat(pattern = "yyyy-MM-dd")
            String createdAt

    ) implements GraduationUserStatusResponse {
        public static Certificate of(GraduationType type, boolean submitted, boolean approval, LocalDateTime createdAt) {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            return Certificate.builder()
                .type(type.name())
                .submitted(submitted)
                .approval(approval)
                .createdAt(createdAt.format(formatter))
                .build();
        }
    }

    @Builder
    @Schema(description = "논문 제출 상태")
    record Thesis(
            @Schema(description = "졸업 타입", example = "THESIS")
            String type,

            @Schema(description = "중간 논문 상태")
            Middle midThesis,

            @Schema(description = "최종 논문 상태")
            Final finalThesis
    ) implements GraduationUserStatusResponse {
        public static Thesis of(GraduationType type, Middle midThesis, Final finalThesis) {
            return Thesis.builder()
                .type(type.name())
                .midThesis(midThesis)
                .finalThesis(finalThesis)
                .build();
        }

        @Builder
        @Schema(description = "중간 논문 제출 상태")
        public record Middle(
                @Schema(description = "파일 제출 여부", example = "true")
                boolean submitted,

                @Schema(description = "승인 여부", example = "true")
                boolean approval,

                @Schema(description = "제출일", example = "2024-08-11", requiredMode = REQUIRED)
                @DateTimeFormat(pattern = "yyyy-MM-dd")
                String createdAt
        ) {
            public static Middle of(boolean submitted, boolean approval, LocalDateTime createdAt) {
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
                return Middle.builder()
                    .submitted(submitted)
                    .approval(approval)
                    .createdAt(createdAt.format(formatter))
                    .build();
            }
        }

        @Builder
        @Schema(description = "최종 논문 제출 상태")
        public record Final(
                @Schema(description = "파일 제출 여부", example = "false")
                boolean submitted,

                @Schema(description = "승인 여부", example = "false")
                boolean approval,

                @Schema(description = "제출일", example = "2024-08-11", requiredMode = REQUIRED)
                @DateTimeFormat(pattern = "yyyy-MM-dd")
                String createdAt
        ) {
            public static Final of(boolean submitted, boolean approval, LocalDateTime createdAt) {
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
                return Final.builder()
                    .submitted(submitted)
                    .approval(approval)
                    .createdAt(createdAt.format(formatter))
                    .build();
            }
        }
    }
}