package kgu.developers.core.domain.user.domain;

public interface UserRepository {
	User save(User user);

	boolean existsByPersonalId(String personalId);
}
