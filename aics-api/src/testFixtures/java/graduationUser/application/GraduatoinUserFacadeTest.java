package graduationUser.application;

import kgu.developers.api.graduationUser.application.GraduationUserFacade;
import kgu.developers.domain.graduationUser.application.command.GraduationUserCommandService;
import kgu.developers.domain.graduationUser.application.query.GraduationUserQueryService;
import kgu.developers.domain.graduationUser.domain.GraduationType;
import kgu.developers.domain.graduationUser.domain.GraduationUser;
import kgu.developers.domain.graduationUser.domain.GraduationUserExcel;
import kgu.developers.domain.graduationUser.infrastructure.excel.GraduationUserExcelImpl;
import kgu.developers.domain.user.application.query.UserQueryService;
import kgu.developers.domain.user.domain.User;
import mock.repository.FakeGraduationUserRepository;
import mock.repository.FakeUserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import static kgu.developers.domain.user.domain.Major.CSE;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class GraduatoinUserFacadeTest {
    private GraduationUserFacade graduationuserFacade;
    private FakeGraduationUserRepository fakeGraduationUserRepository;
    private GraduationUser graduationUser;

    @BeforeEach
    public void init() {
        fakeGraduationUserRepository = new FakeGraduationUserRepository();
        FakeUserRepository fakeUserRepository = new FakeUserRepository();

        GraduationUserExcel graduationUserExcel = new GraduationUserExcelImpl();
        GraduationUserQueryService graduationUserQueryService = new GraduationUserQueryService(fakeGraduationUserRepository,graduationUserExcel);
        GraduationUserCommandService graduationUserCommandService = new GraduationUserCommandService(fakeGraduationUserRepository);

        UserQueryService userQueryService = new UserQueryService(fakeUserRepository);
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        graduationuserFacade = new GraduationUserFacade(graduationUserQueryService,graduationUserCommandService,userQueryService);

        User user = fakeUserRepository.save(User.builder()
            .id("202411345")
            .password(passwordEncoder.encode("password1234"))
            .name("홍길동")
            .email("test@kyonggi.ac.kr")
            .phone("010-1234-5678")
            .major(CSE)
            .build());

        graduationUser = fakeGraduationUserRepository.save(GraduationUser.builder()
                .id(1L)
                .name("홍길동")
                .userId("202411345")
                .email("hong1@kyonggi.ac.kr")
                .build());

        SecurityContext context = SecurityContextHolder.getContext();
        context.setAuthentication(
            new UsernamePasswordAuthenticationToken(user, user.getPassword(), user.getAuthorities())
        );
    }

    @Test
    @DisplayName("updateGraduationType은 Grduation User의 졸업 방식을 선택한다.")
    public void updateGraduationType_Success() {
        //given
        Long graduatoinUserId = 1L;
        GraduationType graduationType = GraduationType.CERTIFICATE;

        //when
        graduationuserFacade.updateGraduationType(graduatoinUserId, graduationType);

        //then
        GraduationUser savedGraduationUser = fakeGraduationUserRepository.findByIdAndDeletedAtIsNull(graduatoinUserId).get();
        assertEquals(graduationType, savedGraduationUser.getGraduationType());
    }

    @Test
    @DisplayName("updateGraduationUserEmail는 GraduationUser의 이메일 속성을 수정하다.")
    public void updateGraduationUserEmail_Success() {
        //given
        Long graduatoinUserId = 1L;
        String email = "soojung@kyonggi.ac.kr";

        //when
        graduationuserFacade.updateGraduationUserEmail(graduatoinUserId, email);

        //then
        GraduationUser savedGraduationUser = fakeGraduationUserRepository.findByIdAndDeletedAtIsNull(graduatoinUserId).get();
        assertEquals(savedGraduationUser.getEmail(), email);

    }
}
