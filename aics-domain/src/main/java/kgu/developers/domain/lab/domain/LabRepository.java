package kgu.developers.domain.lab.domain;

import java.util.List;
import java.util.Optional;

public interface LabRepository {
	Lab save(Lab lab);

	Optional<Lab> findById(Long id);

	List<Lab> findAllByOrderByName();

	void deleteById(Long id);
}
