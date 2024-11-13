package kgu.developers.domain.lab.infrastructure;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import kgu.developers.domain.lab.domain.Lab;

public interface JpaLabRepository extends JpaRepository<Lab, Long> {
	List<Lab> findAllByOrderByName();
}
