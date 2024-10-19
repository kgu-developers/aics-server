package kgu.developers.core.domain.user.infrastructure;

import org.springframework.data.jpa.repository.JpaRepository;

import kgu.developers.core.domain.user.domain.User;

public interface JpaUserRepository extends JpaRepository<User, Long> {
}
