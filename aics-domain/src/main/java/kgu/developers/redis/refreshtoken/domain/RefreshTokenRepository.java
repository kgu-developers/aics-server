package kgu.developers.redis.refreshtoken.domain;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RefreshTokenRepository extends CrudRepository<RefreshToken, String>{
	Optional<RefreshToken> findByRefreshToken(String refreshToken);
}