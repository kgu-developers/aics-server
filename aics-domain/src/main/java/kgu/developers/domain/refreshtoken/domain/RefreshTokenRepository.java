package kgu.developers.domain.refreshtoken.domain;

public interface RefreshTokenRepository {
	void save(String refreshToken, String userId);

	String findUserIdByRefreshToken(String refreshToken);
}
