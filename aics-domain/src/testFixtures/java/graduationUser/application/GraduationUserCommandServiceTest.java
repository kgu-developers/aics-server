package graduationUser.application;

import kgu.developers.domain.graduationUser.application.command.GraduationUserCommandService;
import kgu.developers.domain.graduationUser.domain.GraduationType;
import kgu.developers.domain.graduationUser.domain.GraduationUser;
import kgu.developers.domain.graduationUser.exception.GraduationUserIdDuplicateException;
import kgu.developers.domain.schedule.domain.Schedule;
import kgu.developers.domain.schedule.domain.SubmissionType;
import kgu.developers.domain.user.domain.User;
import mock.repository.FakeGraduationUserRepository;
import mock.repository.FakeUserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.YearMonth;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

public class GraduationUserCommandServiceTest {
    private GraduationUserCommandService graduationUserCommandService;
    private FakeGraduationUserRepository fakeGraduationUserRepository;
    private FakeUserRepository fakeUserRepository;
    private GraduationUser graduationUser;

    private static final Long TARGET_GRADUATION_USER_ID = 2L;
    private static final String TARGET_STUDENT_ID = "202411444";

    @BeforeEach
    public void init() {
        initializeGraduationUserCommandService();
    }

    private void initializeGraduationUserCommandService() {
        fakeUserRepository = new FakeUserRepository();
        fakeGraduationUserRepository = new FakeGraduationUserRepository();
        graduationUserCommandService = new GraduationUserCommandService(fakeGraduationUserRepository);
        saveTestUser();
        graduationUser = fakeGraduationUserRepository.save(saveTestGraduationuser());
    }

    private void saveTestUser() {
        fakeUserRepository.save(User.builder().id(TARGET_STUDENT_ID).build());
        fakeUserRepository.save(User.builder().id("202211444").build());
    }

    private GraduationUser saveTestGraduationuser() {
        return GraduationUser.create("202211444", "홍길동", "김교수", true, "컴퓨터공학과", LocalDate.of(2024, 2, 20));
    }

    @Test
    @DisplayName("createGraduationUser는 GraduationUser를 생성할 수 있다.")
    public void createGraduationUser_Success() {
        //given
        String name = "홍길동";
        String advisor = "김교수";
        Boolean capstoneCompletion = true;
        String department = "컴퓨터공학과";
        YearMonth graduationDate = YearMonth.of(2024, 2);
        //when
        Long createdGraduatoinUserId = graduationUserCommandService.createGraduationUser(TARGET_STUDENT_ID, name, advisor, capstoneCompletion, department, graduationDate);

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
        YearMonth graduationDate = YearMonth.of(2024, 2);
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

    @Test
    @DisplayName("updateGraduationUserEmail은 GraduationUser의 이메일을 저장한다.")
    public void updateGraduationUserEmail_Success() {
        //given
        String email = "example@kyonggi.ac.kr";

        //when
        graduationUserCommandService.updateGraduationUserEmail(graduationUser,email);

        //then
        assertEquals(graduationUser.getEmail(),email);
    }

    @Test
    @DisplayName("updateCertificate은 GraduationUser의 자격증ID를 저장한다.")
    public void updateCertificate_Success() {
        //given
        Long certificateId = 1L;

        //when
        graduationUserCommandService.updateCertificate(graduationUser, certificateId);

        //then
        assertEquals(graduationUser.getCertificateId(),certificateId);
    }

    @Test
    @DisplayName("updateThesis은 주어진 타입에 따라 GraduationUser의 논문ID를 저장한다.")
    public void updateThesis_Success() {
        //given
        Long midThesisId = 1L;
        Long finalThesisId = 2L;
        Schedule midSchedule = Schedule.builder()
            .id(1L)
            .submissionType(SubmissionType.MIDTHESIS)
            .build();

        Schedule finalSchedule = Schedule.builder()
            .id(1L)
            .submissionType(SubmissionType.FINALTHESIS)
            .build();

        //when
        graduationUserCommandService.updateThesis(graduationUser, midThesisId, midSchedule);
        graduationUserCommandService.updateThesis(graduationUser, finalThesisId, finalSchedule);

        //then
        assertEquals(graduationUser.getMidThesisId(), midThesisId);
        assertEquals(graduationUser.getFinalThesisId(), finalThesisId);
    }

}
