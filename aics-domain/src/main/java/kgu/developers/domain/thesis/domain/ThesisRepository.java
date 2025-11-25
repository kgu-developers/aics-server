package kgu.developers.domain.thesis.domain;

import java.util.Optional;

public interface ThesisRepository {
	Long save(Thesis thesis);

    Optional<Thesis> findByIdAndDeletedAtIsNull(Long thesisId);

    Optional<Boolean> findApprovalByIdAndDeletedAtIsNull(Long id);
}
