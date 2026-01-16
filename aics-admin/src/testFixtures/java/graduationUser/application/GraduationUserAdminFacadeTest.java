package graduationUser.application;

import kgu.developers.admin.graduationUser.application.GraduationUserAdminFacade;
import kgu.developers.admin.graduationUser.presentation.request.GraduationUserBatchApproveRequest;
import kgu.developers.admin.graduationUser.presentation.request.GraduationUserBatchCreateRequest;
import kgu.developers.admin.graduationUser.presentation.request.GraduationUserBatchDeleteRequest;
import kgu.developers.admin.graduationUser.presentation.request.GraduationUserCreateRequest;
import kgu.developers.admin.graduationUser.presentation.response.GraduationUserBatchCreateResponse;
import kgu.developers.admin.graduationUser.presentation.response.GraduationUserDetailResponse;
import kgu.developers.admin.graduationUser.presentation.response.GraduationUserPersistResponse;
import kgu.developers.admin.graduationUser.presentation.response.GraduationUserSummaryPageResponse;
import kgu.developers.admin.graduationUser.presentation.response.GraduationUserSummaryResponse;
import kgu.developers.common.response.PageableResponse;
import kgu.developers.domain.certificate.application.command.CertificateCommandService;
import kgu.developers.domain.certificate.application.query.CertificateQueryService;
import kgu.developers.domain.certificate.domain.Certificate;
import kgu.developers.domain.file.application.command.FileCommandService;
import kgu.developers.domain.file.infrastructure.ImageResizingServiceImpl;
import kgu.developers.domain.file.infrastructure.properties.FilePathProperties;
import kgu.developers.domain.file.infrastructure.repository.FileStorageServiceImpl;
import kgu.developers.domain.graduationUser.application.command.GraduationUserCommandService;
import kgu.developers.domain.graduationUser.application.query.GraduationUserQueryService;
import kgu.developers.domain.graduationUser.domain.GraduationType;
import kgu.developers.domain.graduationUser.domain.GraduationUser;
import kgu.developers.domain.graduationUser.domain.GraduationUserExcel;
import kgu.developers.domain.graduationUser.infrastructure.excel.GraduationUserExcelImpl;
import kgu.developers.domain.schedule.application.query.ScheduleQueryService;
import kgu.developers.domain.thesis.application.command.ThesisCommandService;
import kgu.developers.domain.thesis.application.query.ThesisQueryService;
import kgu.developers.domain.thesis.domain.Thesis;
import kgu.developers.domain.user.application.query.UserQueryService;
import kgu.developers.domain.user.domain.User;
import mock.repository.FakeCertificateRepository;
import mock.repository.FakeFileRepository;
import mock.repository.FakeGraduationUserRepository;
import mock.repository.FakeScheduleRepository;
import mock.repository.FakeThesisRepository;
import mock.repository.FakeUserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.time.LocalDate;
import java.time.YearMonth;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class GraduationUserAdminFacadeTest {
    private GraduationUserAdminFacade graduationUserAdminFacade;

    private FakeUserRepository fakeUserRepository;
    private FakeThesisRepository fakeThesisRepository;
    private FakeCertificateRepository fakeCertificateRepository;
    private FakeScheduleRepository fakeScheduleRepository;

    private GraduationUser graduationUser1;
    private GraduationUser graduationUser2;

    @BeforeEach
    public void init() {
        FakeGraduationUserRepository fakeGraduationUserRepository = new FakeGraduationUserRepository();
        fakeUserRepository = new FakeUserRepository();
        fakeScheduleRepository = new FakeScheduleRepository();
        GraduationUserCommandService graduationUserCommandService = new GraduationUserCommandService(
            fakeGraduationUserRepository,
            new ScheduleQueryService(fakeScheduleRepository)
        );

        fakeThesisRepository = new FakeThesisRepository();
        fakeCertificateRepository = new FakeCertificateRepository();

        UserQueryService userQueryService = new UserQueryService(fakeUserRepository);

        GraduationUserExcel graduationUserExcel = new GraduationUserExcelImpl();
        GraduationUserQueryService graduationUserQueryService = new GraduationUserQueryService(userQueryService, fakeGraduationUserRepository, fakeThesisRepository, fakeCertificateRepository, graduationUserExcel);

        FakeFileRepository fakeFileRepository = new FakeFileRepository();
        FakeScheduleRepository fakeScheduleRepository = new FakeScheduleRepository();
        FilePathProperties filePathProperties = new FilePathProperties();
        filePathProperties.setUploadPath("src/test/resources/uploads");
        filePathProperties.setUrl("http://test-url.com");
        filePathProperties.setDisallowedExtensions(new HashSet<>());

        FileStorageServiceImpl fileStorageService = new FileStorageServiceImpl(filePathProperties, new ImageResizingServiceImpl());
        FileCommandService fileCommandService = new FileCommandService(fakeFileRepository);
        ScheduleQueryService scheduleQueryService = new ScheduleQueryService(fakeScheduleRepository);

        ThesisCommandService thesisCommandService = new ThesisCommandService(
            fakeThesisRepository,
            fileStorageService,
            fileCommandService,
            scheduleQueryService,
            graduationUserQueryService
        );

        CertificateCommandService certificateCommandService = new CertificateCommandService(
            fakeCertificateRepository,
            fileStorageService,
            fileCommandService,
            scheduleQueryService,
            graduationUserQueryService
        );

        fakeThesisRepository.save(
            Thesis.builder()
                .id(1L)
                .thesisFileId(1L)
                .approval(false)
                .build()
        );

        fakeThesisRepository.save(
            Thesis.builder()
                .id(2L)
                .thesisFileId(2L)
                .approval(false)
                .build()
        );

        fakeCertificateRepository.save(
            Certificate.builder()
                .id(1L)
                .certificateFileId(1L)
                .approval(false)
                .build()
        );



        graduationUserAdminFacade = new GraduationUserAdminFacade(
            graduationUserCommandService,
            graduationUserQueryService,
            thesisCommandService,
            new ThesisQueryService(fakeThesisRepository),
            certificateCommandService,
            new CertificateQueryService(fakeCertificateRepository)
        );

        graduationUser1 = fakeGraduationUserRepository.save(GraduationUser.builder()
            .id(1L)
            .name("홍길동")
            .userId("202411001")
            .email("hong1@kyonggi.ac.kr")
            .graduationType(GraduationType.CERTIFICATE)
            .certificateId(1L)
            .graduationDate(LocalDate.of(2021, 12, 1))
            .build());

        graduationUser2 = fakeGraduationUserRepository.save(GraduationUser.builder()
            .id(2L)
            .name("이영희")
            .userId("202411002")
            .email("young1@kyonggi.ac.kr")
            .graduationType(GraduationType.THESIS)
            .midThesisId(1L)
            .finalThesisId(2L)
            .graduationDate(LocalDate.of(2021, 12, 1))
            .build());

        fakeGraduationUserRepository.save(GraduationUser.builder()
            .id(3L)
            .name("이지민")
            .userId("202411003")
            .email("jiim1@kyonggi.ac.kr")
            .graduationDate(LocalDate.of(2021, 12, 1))
            .build());
    }


    @Test
    @DisplayName("createGraduationUser는 GraduationUser를 생성한다.")
    public void createGraduationUser_Success() {
        //given
        fakeUserRepository.save(User.builder().id("202411346").build());
        GraduationUserCreateRequest request = GraduationUserCreateRequest.builder()
            .studentId("202411346")
            .name("홍길순")
            .advisorProfessor("김교수")
            .capstoneCompletion(false)
            .graduationDate(YearMonth.of(2021, 12))
            .build();

        //when
        GraduationUserPersistResponse result = graduationUserAdminFacade.createGraduationUser(request);

        //then
        assertEquals(1L,result.id());
    }

    @Test
    @DisplayName("createGraduationUsers은 여러 GraduationUser를 생성한다.")
    public void createGraduationUsers_Success() {
        //given
        fakeUserRepository.save(User.builder().id("202411346").build());
        fakeUserRepository.save(User.builder().id("202411347").build());
        List<GraduationUserCreateRequest> requestList = new ArrayList<>();
        GraduationUserCreateRequest requestUser1 = GraduationUserCreateRequest.builder()
                .studentId("202411346")
                .name("홍길순")
                .capstoneCompletion(false)
                .graduationDate(YearMonth.of(2021, 12))
                .build();
        requestList.add(requestUser1);

        GraduationUserCreateRequest requestUser2 = GraduationUserCreateRequest.builder()
                .studentId("202411347")
                .name("홍길동")
                .capstoneCompletion(true)
                .graduationDate(YearMonth.of(2028, 12))
                .build();
        requestList.add(requestUser2);

        GraduationUserBatchCreateRequest request = GraduationUserBatchCreateRequest.builder()
                .graduationUsers(requestList)
                .build();

        //when
        GraduationUserBatchCreateResponse response = graduationUserAdminFacade.createGraduationUsers(request);

        //then
        assertEquals(response.createdIds().get(0),1L);
        assertEquals(response.createdIds().get(1),2L);

    }

    @Test
    @DisplayName("getGraduationUsersByNameAndGraduationType는 GraduationUser를 페이징해서 조회한다.")
    public void getGraduationUsersByNameAndGraduationType_Success() {
        //given
        Pageable pageable = PageRequest.of(0, 10);

        //when
        GraduationUserSummaryPageResponse result = graduationUserAdminFacade.getGraduationUsersByNameAndGraduationType(pageable,null,null);

        //then
        List<GraduationUserSummaryResponse> resultData = result.contents();
        PageableResponse pageableResult = result.pageable();

        assertEquals(10,pageableResult.size());
        assertEquals(3, resultData.size());
        assertEquals(graduationUser1.getName(),resultData.get(0).name());
        assertEquals(graduationUser2.getName(),resultData.get(1).name());
    }

    @Test
    @DisplayName("deleteGraduationUser는 주어진 GraduationUser를 삭제한다.")
    public void deleteGraduationUser_Success() {
        //given
        Long deletedGraduationUserId = 1L;

        //when
        graduationUserAdminFacade.deleteGraduationUser(deletedGraduationUserId);

        //then
        assertNotNull(graduationUser1.getDeletedAt());
    }

    @Test
    @DisplayName("getGrduationUserById는 GraduatoinUser를 id를 통해 조회한다.")
    public void getGrduationUserById_Success() {
        //given
        Long graduationUserId = 1L;

        //when
        GraduationUserDetailResponse result = graduationUserAdminFacade.getGraduationUserById(graduationUserId);

        //then
        assertEquals(result.studentId(),graduationUser1.getUserId());
    }

    @Test
    @DisplayName("deleteGraduationUsers는 여러 GraduationUser를 삭제한다.")
    public void deleteGraduationUsers_Success() {
        //given
        List<Long> graduationUserIds = Arrays.asList(1L, 2L);

        GraduationUserBatchDeleteRequest request = GraduationUserBatchDeleteRequest.builder()
            .ids(graduationUserIds)
            .build();

        //when
        graduationUserAdminFacade.deleteGraduationUsers(request);

        //then
        assertNotNull(graduationUser1.getDeletedAt());
        assertNotNull(graduationUser2.getDeletedAt());
    }


    @Test
    @DisplayName("approveGraduationUsers는 여러 GraduatoinUser의 제출을 승인한다.")
    public void approveGraduationUsers_Success() {
        //given
        List<Long> graduationUserIds = Arrays.asList(1L, 2L);

        GraduationUserBatchApproveRequest request = GraduationUserBatchApproveRequest.builder()
            .ids(graduationUserIds)
            .build();

        //when
        graduationUserAdminFacade.approveGraduationUsers(request);

        //then
        assertEquals(true,fakeThesisRepository.findApprovalByIdAndDeletedAtIsNull(1L).get());
        assertEquals(true,fakeThesisRepository.findApprovalByIdAndDeletedAtIsNull(2L).get());
        assertEquals(true,fakeCertificateRepository.findApprovalByIdAndDeletedAtIsNull(1L).get());
    }

}
