package graduationUser.application;

import kgu.developers.domain.graduationUser.application.command.GraduationUserCommandService;
import kgu.developers.domain.graduationUser.domain.GraduationType;
import kgu.developers.domain.graduationUser.domain.GraduationUser;
import kgu.developers.domain.graduationUser.exception.GraduationUserIdDuplicateException;
import mock.repository.FakeGraduationUserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

public class GraduationUserCommandServiceTest {
    private GraduationUserCommandService graduationUserCommandService;
    private FakeGraduationUserRepository fakeGraduationUserRepository;
    private GraduationUser graduationUser;

    private static final Long TARGET_GRADUATION_USER_ID = 2L;

    @BeforeEach
    public void init() {
        initializeGraduationUserCommandService();
    }

    private void initializeGraduationUserCommandService() {
        fakeGraduationUserRepository = new FakeGraduationUserRepository();
        graduationUserCommandService = new GraduationUserCommandService(fakeGraduationUserRepository);
        graduationUser = fakeGraduationUserRepository.save(saveTestGraduatoinuser());
    }

    private GraduationUser saveTestGraduatoinuser() {
        return GraduationUser.create("202211444", "홍길동", "김교수", true, "컴퓨터공학과", LocalDate.of(2024, 2, 20));
    }

    @Test
    @DisplayName("createGraduationUser는 GraduationUser를 생성할 수 있다.")
    public void createGraduationUser_Success() {
        //given
        String studentId = "202411444";
        String name = "홍길동";
        String advisor = "김교수";
        Boolean capstoneCompletion = true;
        String department = "컴퓨터공학과";
        LocalDate graduationDate = LocalDate.of(2024, 2, 20);
        //when
        Long createdGraduatoinUserId = graduationUserCommandService.createGraduationUser(studentId, name, advisor, capstoneCompletion, department, graduationDate);

        //then
        assertEquals(TARGET_GRADUATION_USER_ID, createdGraduatoinUserId);
    }

    @Test
    @DisplayName("createGraduationUser는 중복된 아이디로 유저를 생성할 경우 GraduationUserIdDuplicateException을 발생시킨다.")
    public void createGraduationUser_DuplicateId_ThrowsException() {
        //given
        String studentId = "202211444";
        String name = "홍길동";
        String advisor = "김교수";
        Boolean capstoneCompletion = true;
        String department = "컴퓨터공학과";
        LocalDate graduationDate = LocalDate.of(2024, 2, 20);
        //when
        //then
        assertThatThrownBy(() -> graduationUserCommandService.createGraduationUser(studentId, name, advisor, capstoneCompletion, department, graduationDate))
            .isInstanceOf(GraduationUserIdDuplicateException.class);
    }

    @Test
    @DisplayName("updateGraduationType는 Graduation User의 졸업 방식을 선택할 수 있다.")
    public void updateGraduationType_Success() {
        //given
        GraduationType selectedType = GraduationType.CERTIFICATE;
        //when
        graduationUserCommandService.updateGraduationType(graduationUser, selectedType);
        //then
        assertEquals(selectedType, graduationUser.getGraduationType());
    }

    @Test
    @DisplayName("deleteGraduationUser는 Graduation User의 deletedAt을 설정한다.")
    public void deleteGraduationUser_Success() {
        //given
        //when
        assertNull(graduationUser.getDeletedAt());
        graduationUserCommandService.deleteGraduationUser(graduationUser);

        //then
        assertNotNull(graduationUser.getDeletedAt());
    }
}
