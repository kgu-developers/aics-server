package kgu.developers.domain.club.infrastructure;

import org.springframework.data.jpa.repository.JpaRepository;

import kgu.developers.domain.club.domain.Club;

public interface JpaClubRepository extends JpaRepository<Club, Long> {
}
