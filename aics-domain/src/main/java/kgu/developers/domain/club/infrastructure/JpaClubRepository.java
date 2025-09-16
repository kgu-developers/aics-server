package kgu.developers.domain.club.infrastructure;

import org.springframework.data.jpa.repository.JpaRepository;

public interface JpaClubRepository extends JpaRepository<ClubJpaEntity, Long> {
}
