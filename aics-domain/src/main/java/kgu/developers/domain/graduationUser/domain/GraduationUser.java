package kgu.developers.domain.graduationUser.domain;

import kgu.developers.domain.graduationUser.exception.GraduationUserMismatchException;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Builder
@AllArgsConstructor
public class GraduationUser {
    private Long id;
    private String name;
    private String email;
    private String advisorProfessor;
    private GraduationType graduationType;
    private LocalDate graduationDate;
    private Boolean capstoneCompletion;
    private String department;
    private Long midThesisId;
    private Long finalThesisId;
    private Long certificateId;
    private String userId;

    protected LocalDateTime createdAt;
    protected LocalDateTime updatedAt;
    protected LocalDateTime deletedAt;

    public static GraduationUser create(String studentId, String name, String advisor, Boolean capstoneCompletion, String department, LocalDate graduationDate) {
        return GraduationUser.builder()
            .userId(studentId)
            .name(name)
            .advisorProfessor(advisor)
            .capstoneCompletion(capstoneCompletion)
            .department(department)
            .graduationDate(graduationDate)
            .build();
    }

    public void validateAccessPermission(String id) {
        if(!this.userId.equals(id)) {
            throw new GraduationUserMismatchException();
        }
    }

    public void updateGraduationType(GraduationType type) {
        this.graduationType = type;
    }

    public void delete() {
        deletedAt = LocalDateTime.now();
    }

    public void updateEmail(String email) {
        this.email = email;
    }

    public void updateCertificate(Long certificateId) {
        this.certificateId = certificateId;
    }

    public void updateMidThesisId(Long thesisId) {
        this.midThesisId = thesisId;
    }

    public void updateFinalThesisId(Long thesisId) {
        this.finalThesisId = thesisId;
    }
}
