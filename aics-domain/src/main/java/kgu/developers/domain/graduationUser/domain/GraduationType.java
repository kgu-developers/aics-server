package kgu.developers.domain.graduationUser.domain;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum GraduationType {
        THESIS("보고서"),
        CERTIFICATE("자격증"),
        ;

        private final String description;
}
