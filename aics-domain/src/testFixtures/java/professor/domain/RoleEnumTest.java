package professor.domain;

import kgu.developers.domain.professor.domain.Role;
import kgu.developers.domain.professor.exception.NoSuchRoleException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class RoleEnumTest {

	@Test
	@DisplayName("Role은 description으로 생성할 수 있다")
	public void of_Success() {
		// given
		String description1 = "교수";
		String description2 = "조교수";

		// when
		Role role1 = Role.of(description1);
		Role role2 = Role.of(description2);

		// then
		assertEquals("PROFESSOR", role1.name());
		assertEquals("ASSISTANT", role2.name());
		assertEquals(description1, role1.getDescription());
		assertEquals(description2, role2.getDescription());
	}


	@Test
	@DisplayName("Role은 description이 올바르지 않으면 NoSuchRoleException을 반환한다")
	public void of_throws_NoSuchRoleException() {
		// given
		String description = "학생";

		// when
		// then
		assertThrows(NoSuchRoleException.class, () -> Role.of(description));
	}
}
