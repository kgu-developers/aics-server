package kgu.developers.domain.lab.infrastructure;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface JpaLabRepository extends JpaRepository<LabJpaEntity, Long> {
	List<LabJpaEntity> findAllByOrderByName();
}
