package refreshtoken.domain;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import kgu.developers.domain.refreshtoken.domain.RefreshToken;
import org.junit.jupiter.api.Test;

public class RefreshTokenDomainTest {
	@Test
	public void createRefreshToken_Success() {
		//given
		String token = "valid.refresh.token";
		String userId = "userId";

		//when
		RefreshToken refreshToken = RefreshToken.of(token, userId);

		//then
		assertNotNull(refreshToken);
		assertEquals(token, refreshToken.getRefreshToken());
		assertEquals(userId, refreshToken.getUserId());
	}
}
