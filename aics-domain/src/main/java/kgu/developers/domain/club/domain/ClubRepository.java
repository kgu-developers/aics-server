package kgu.developers.domain.club.domain;

import java.util.List;

public interface ClubRepository {
	Club save(Club club);

	List<Club> findAll();
}
