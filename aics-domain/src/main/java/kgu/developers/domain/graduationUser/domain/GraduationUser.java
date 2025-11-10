package kgu.developers.domain.graduationUser.domain;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Builder
public class GraduationUser {
    private Long id;
    private String name;
    private String email;
    private String advisorProfessor;
    private GraduationType graduationType;
    private LocalDate graduationDate;
    private Long midThesisId;
    private Long finalThesisId;
    private Long certificateId;
    private String userId;

    protected LocalDateTime createdAt;
    protected LocalDateTime updatedAt;
    protected LocalDateTime deletedAt;
}
