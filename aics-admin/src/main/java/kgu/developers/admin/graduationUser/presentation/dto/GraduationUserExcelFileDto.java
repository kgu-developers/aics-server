package kgu.developers.admin.graduationUser.presentation.dto;

import lombok.Builder;

@Builder
public record GraduationUserExcelFileDto(
    byte[] content,
    String filename
) {
    public static GraduationUserExcelFileDto from(byte[] content, String filename) {
        return GraduationUserExcelFileDto.builder()
            .content(content)
            .filename(filename)
            .build();
    }
}
