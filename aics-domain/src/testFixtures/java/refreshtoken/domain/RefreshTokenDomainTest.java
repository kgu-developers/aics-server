package refreshtoken.domain;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import kgu.developers.domain.refreshtoken.domain.RefreshToken;
import org.junit.jupiter.api.Test;

public class RefreshTokenDomainTest {
	@Test
	public void createRefreshToken_Success() throws Exception {
		//given
		String refreshToken = "valid.refresh.token";
		String userId = "userId";

		//when
		//then
		assertNotNull(RefreshToken.of(refreshToken, userId));
	}
}
