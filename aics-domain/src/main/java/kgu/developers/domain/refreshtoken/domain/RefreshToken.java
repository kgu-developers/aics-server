package kgu.developers.domain.refreshtoken.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;
import org.springframework.data.redis.core.index.Indexed;

@Getter
@RedisHash(value = "refreshToken", timeToLive = 60 * 60 * 24 * 7)
@Builder
@AllArgsConstructor
public class RefreshToken {
	@Id
	private String userId;
	@Indexed
	private String refreshToken;

	public static RefreshToken of(String userId, String refreshToken) {
		return RefreshToken.builder()
			.userId(userId)
			.refreshToken(refreshToken)
			.build();
	}
}
