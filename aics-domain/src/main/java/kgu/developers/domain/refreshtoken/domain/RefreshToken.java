package kgu.developers.domain.refreshtoken.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;

@Getter
@RedisHash(value = "refreshToken", timeToLive = 60 * 60 * 24 * 7)
@Builder
@AllArgsConstructor
public class RefreshToken {
	@Id
	private String refreshToken;
	private String userId;

	public static RefreshToken of(String refreshToken, String userId) {
		return RefreshToken.builder()
			.refreshToken(refreshToken)
			.userId(userId)
			.build();
	}
}
