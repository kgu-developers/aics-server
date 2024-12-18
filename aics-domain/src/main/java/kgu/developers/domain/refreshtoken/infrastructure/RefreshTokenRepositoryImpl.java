package kgu.developers.domain.refreshtoken.infrastructure;

import kgu.developers.domain.refreshtoken.domain.RefreshToken;
import kgu.developers.domain.refreshtoken.domain.RefreshTokenRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

import java.time.Duration;

@Repository
@RequiredArgsConstructor
public class RefreshTokenRepositoryImpl implements RefreshTokenRepository {
	private final RedisTemplate<String, String> redisTemplate;

	private final String REFRESH_TOKEN_KEY_PREFIX = "refresh_token:";

	@Override
	public void save(RefreshToken refreshToken) {
		String token = refreshToken.getRefreshToken();
		String userId = refreshToken.getUserId();
		redisTemplate.opsForValue().set(REFRESH_TOKEN_KEY_PREFIX + token, userId);
		redisTemplate.expire(REFRESH_TOKEN_KEY_PREFIX + token, Duration.ofDays(7));
	}

	@Override
	public String findUserIdByRefreshToken(String refreshToken) {
		return redisTemplate.opsForValue().get(REFRESH_TOKEN_KEY_PREFIX + refreshToken);
	}
}
