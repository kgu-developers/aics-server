package kgu.developers.domain.lab.infrastructure;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface JpaLabRepository extends JpaRepository<LabEntity, Long> {
	List<LabEntity> findAllByOrderByName();
}
