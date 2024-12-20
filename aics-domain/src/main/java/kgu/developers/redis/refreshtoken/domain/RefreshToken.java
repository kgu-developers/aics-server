package kgu.developers.redis.refreshtoken.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.redis.core.RedisHash;

@Entity
@Getter
@RedisHash(value = "refreshToken", timeToLive = 60 * 60 * 24 * 7)
@Builder
@NoArgsConstructor
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
