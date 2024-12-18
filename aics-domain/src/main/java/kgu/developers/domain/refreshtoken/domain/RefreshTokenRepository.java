package kgu.developers.domain.refreshtoken.domain;

public interface RefreshTokenRepository {
	void save(RefreshToken refreshToken);

	String findUserIdByRefreshToken(String token);
}
