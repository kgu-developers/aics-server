package mock;

import kgu.developers.domain.refreshtoken.domain.RefreshToken;
import kgu.developers.domain.refreshtoken.domain.RefreshTokenRepository;

import java.util.HashMap;
import java.util.Map;

public class FakeRefreshTokenRepository implements RefreshTokenRepository {
	Map<String, String> fakeRedis = new HashMap<>();

	@Override
	public void save(RefreshToken refreshToken) {
		fakeRedis.put(refreshToken.getRefreshToken(), refreshToken.getUserId());
	}

	@Override
	public String findUserIdByRefreshToken(String token) {
		return fakeRedis.get(token);
	}
}
