package kgu.developers.domain.club.domain;

import java.util.List;
import java.util.Optional;

public interface ClubRepository {
	Club save(Club club);

	List<Club> findAll();

	Optional<Club> findById(Long id);

	void deleteById(Long id);
}
