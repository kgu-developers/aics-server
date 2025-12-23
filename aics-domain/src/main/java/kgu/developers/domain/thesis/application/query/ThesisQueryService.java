package kgu.developers.domain.thesis.application.query;

import kgu.developers.domain.thesis.domain.Thesis;
import kgu.developers.domain.thesis.domain.ThesisRepository;
import kgu.developers.domain.thesis.exception.ThesisNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ThesisQueryService {
    private final ThesisRepository thesisRepository;

    public Thesis getById(Long id) {
        return thesisRepository.findByIdAndDeletedAtIsNull(id)
                .orElseThrow(ThesisNotFoundException::new);
    }
}
