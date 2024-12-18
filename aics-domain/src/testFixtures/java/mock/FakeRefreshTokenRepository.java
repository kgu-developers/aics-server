package mock;

import kgu.developers.domain.refreshtoken.domain.RefreshTokenRepository;

import java.util.HashMap;
import java.util.Map;

public class FakeRefreshTokenRepository implements RefreshTokenRepository {
	Map<String, String> fakeRedis = new HashMap<>();

	@Override
	public void save(String refreshToken, String userId) {
		fakeRedis.put(refreshToken, userId);
	}

	@Override
	public String findUserIdByRefreshToken(String refreshToken) {
		return fakeRedis.get(refreshToken);
	}
}
