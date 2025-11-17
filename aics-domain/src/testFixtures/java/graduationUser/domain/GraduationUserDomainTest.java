package graduationUser.domain;

import kgu.developers.domain.graduationUser.domain.GraduationType;
import kgu.developers.domain.graduationUser.domain.GraduationUser;
import kgu.developers.domain.graduationUser.exception.GraduationUserMismatchException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class GraduationUserDomainTest {
    private GraduationUser graduationUser;
    private static final String STUDENT_ID = "202411444";
    private static final String NAME = "홍길동";
    private static final String ADVISOR = "김교수";
    private static final Boolean CAPSTONE_COMPLETION = true;
    private static final String DEPARTMENT = "컴퓨터공학과";
    private static final LocalDate GRADUATION_DATE = LocalDate.of(2024, 2, 20);

    @BeforeEach
    public void init() {
        graduationUser = GraduationUser.create(STUDENT_ID, NAME, ADVISOR, CAPSTONE_COMPLETION, DEPARTMENT, GRADUATION_DATE);
    }

    @Test
    @DisplayName("GRADUATION USER 객체를 생성할 수 있다.")
    public void createGraduationUser_Success() {
        //when
        //then
        assertNotNull(graduationUser);
        assertEquals(STUDENT_ID,graduationUser.getUserId());
        assertEquals(NAME,graduationUser.getName());
        assertEquals(ADVISOR,graduationUser.getAdvisorProfessor());
        assertEquals(CAPSTONE_COMPLETION,graduationUser.getCapstoneCompletion());
        assertEquals(DEPARTMENT,graduationUser.getDepartment());
        assertEquals(GRADUATION_DATE,graduationUser.getGraduationDate());

    }

    @Test
    @DisplayName("GRADUATION USER 접근 권한을 확인할 수 있다.")
    public void validateGraduationUser_Success() {
        //given
        String otherUserId = "202411400";

        //when
        //then
        assertDoesNotThrow(() -> graduationUser.validateAccessPermission(STUDENT_ID));
        assertThrows(GraduationUserMismatchException.class,
            () -> graduationUser.validateAccessPermission(otherUserId));
    }

    @Test
    @DisplayName("졸업 유형을 변경할 수 있다")
    void updateGraduationType_Success() {
        // given
        GraduationType newType = GraduationType.CERTIFICATE;

        // when
        graduationUser.updateGraduationType(newType);

        // then
        assertEquals(newType, graduationUser.getGraduationType());
    }

    @Test
    @DisplayName("졸업대상자를 삭제하면 deletedAt이 설정된다")
    void delete_Success() {
        // given

        // when
        graduationUser.delete();

        // then
        assertNotNull(graduationUser.getDeletedAt());
    }
}
