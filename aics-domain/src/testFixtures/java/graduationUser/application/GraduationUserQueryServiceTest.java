package graduationUser.application;

import kgu.developers.common.response.PaginatedListResponse;
import kgu.developers.domain.graduationUser.application.query.GraduationUserQueryService;
import kgu.developers.domain.graduationUser.domain.GraduationUser;
import kgu.developers.domain.graduationUser.domain.GraduationUserExcel;
import kgu.developers.domain.graduationUser.infrastructure.excel.GraduationUserExcelImpl;
import mock.repository.FakeCertificateRepository;
import mock.repository.FakeGraduationUserRepository;
import mock.repository.FakeThesisRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.data.domain.PageRequest;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class GraduationUserQueryServiceTest {
    private GraduationUserQueryService graduationUserQueryService;
    private static final Long TARGET_GRADUATION_USER_ID = 1L;
    private static final String TARGET_STUDENT_ID = "202211444";

    @BeforeEach
    public void init() {
        FakeGraduationUserRepository fakeGraduationUserRepository = new FakeGraduationUserRepository();

        graduationUserQueryService = new GraduationUserQueryService(
                fakeGraduationUserRepository,
                new FakeThesisRepository(),
                new FakeCertificateRepository(),
                new GraduationUserExcelImpl());

        fakeGraduationUserRepository.save(GraduationUser.create(
            "202211444", "홍길동", "김교수", true, "컴퓨터공학과", LocalDate.of(2024, 2, 20)
        ));

        fakeGraduationUserRepository.save(GraduationUser.create(
            "202213678", "박민수", "최교수", false, "컴퓨터공학과", LocalDate.of(2024, 8, 15)
        ));

        fakeGraduationUserRepository.save(GraduationUser.create(
            "202112345", "이영희", "박교수", true, "컴퓨터공학과", LocalDate.of(2024, 2, 20)
        ));

        fakeGraduationUserRepository.save(GraduationUser.create(
            "202111223", "김철수", "정교수", true, "정보보안학과", LocalDate.of(2023, 8, 20)
        ));

        fakeGraduationUserRepository.save(GraduationUser.create(
            "202214567", "정수진", "이교수", false, "인공지능학과", LocalDate.of(2024, 2, 20)
        ));
    }

    @Test
    @DisplayName("getById는 해당 id의 GraduationUser를 반환한다.")
    public void getById_Success() {
        //given
        //when
        GraduationUser graduationUser = graduationUserQueryService.getById(TARGET_GRADUATION_USER_ID);

        //then
        assertEquals(TARGET_GRADUATION_USER_ID, graduationUser.getId());
        assertEquals(TARGET_STUDENT_ID, graduationUser.getUserId());

    }

    @Test
    @DisplayName("getGraduationUsersByNameAndGraduationType은 졸업 대상자를 페이징 조회 할 수 있다.")
    public void getGraduationUsersByNameAndGraduationType_Success() {
        //given
        String name = "정수진";
        int page = 0;
        int size = 10;
        //when
        PaginatedListResponse<GraduationUser> result = graduationUserQueryService.getGraduationUsersByNameAndGraduationType(
            PageRequest.of(page,size),name,null
        );

        //then
        assertEquals(1,result.contents().size());

    }

    @Test
    @DisplayName("getByUserId는 해당 학번의 졸업 대상자를 반환한다.")
    public void getByUserId_Success() {
        //given
        //when
        GraduationUser graduationUser = graduationUserQueryService.getByUserId(TARGET_STUDENT_ID);

        //then
        assertEquals(TARGET_GRADUATION_USER_ID, graduationUser.getId());
        assertEquals(TARGET_STUDENT_ID, graduationUser.getUserId());

    }
}
