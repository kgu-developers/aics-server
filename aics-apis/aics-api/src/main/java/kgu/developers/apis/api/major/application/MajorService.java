package kgu.developers.apis.api.major.application;

import kgu.developers.apis.api.major.presentation.exception.MajorDuplicatedException;
import kgu.developers.apis.api.major.presentation.request.MajorCreateRequest;
import kgu.developers.apis.api.major.presentation.response.MajorPersistResponse;
import kgu.developers.core.domain.major.domain.Major;
import kgu.developers.core.domain.major.domain.MajorRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class MajorService {
    private final MajorRepository majorRepository;

    @Transactional
    public MajorPersistResponse createMajor(MajorCreateRequest request) {
        String inutToString = request.name().toUpperCase();
        validateIsDuplicatedMajor(inutToString);
        Major saved = majorRepository.save(Major.create(inutToString));
        return MajorPersistResponse.of(saved.getId(), saved.getName());
    }

    private void validateIsDuplicatedMajor(String majorName) {
        if (majorRepository.existByName(majorName)) {
            throw new MajorDuplicatedException();
        }
    }
}
