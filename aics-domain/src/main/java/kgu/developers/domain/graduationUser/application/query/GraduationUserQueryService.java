package kgu.developers.domain.graduationUser.application.query;

import kgu.developers.domain.graduationUser.domain.GraduationUser;
import kgu.developers.domain.graduationUser.domain.GraduationUserRepository;
import kgu.developers.domain.graduationUser.exception.GraduationUserNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class GraduationUserQueryService {
    private final GraduationUserRepository graduationUserRepository;

    public GraduationUser getById(Long graduationUserId) {
        return graduationUserRepository.findByIdAndDeletedAtIsNull(graduationUserId)
            .orElseThrow(GraduationUserNotFoundException::new);
    }
}
