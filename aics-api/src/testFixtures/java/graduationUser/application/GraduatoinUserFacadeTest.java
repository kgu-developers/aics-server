package graduationUser.application;

import kgu.developers.api.graduationUser.application.GraduationuserFacade;
import kgu.developers.domain.graduationUser.application.command.GraduationUserCommandService;
import kgu.developers.domain.graduationUser.application.query.GraduationUserQueryService;
import kgu.developers.domain.graduationUser.domain.GraduationType;
import kgu.developers.domain.graduationUser.domain.GraduationUser;
import kgu.developers.domain.graduationUser.domain.GraduationUserExcel;
import kgu.developers.domain.graduationUser.infrastructure.excel.GraduationUserExcelImpl;
import kgu.developers.domain.user.application.query.UserQueryService;
import kgu.developers.domain.user.domain.User;
import mock.repository.FakeGraduatoinUserRepository;
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
    private GraduationuserFacade graduationuserFacade;
    private FakeGraduatoinUserRepository fakeGraduatoinUserRepository;
    private GraduationUser graduationUser;

    @BeforeEach
    public void init() {
        fakeGraduatoinUserRepository = new FakeGraduatoinUserRepository();
        FakeUserRepository fakeUserRepository = new FakeUserRepository();

        GraduationUserExcel graduationUserExcel = new GraduationUserExcelImpl();
        GraduationUserQueryService graduationUserQueryService = new GraduationUserQueryService(fakeGraduatoinUserRepository,graduationUserExcel);
        GraduationUserCommandService graduationUserCommandService = new GraduationUserCommandService(fakeGraduatoinUserRepository);

        UserQueryService userQueryService = new UserQueryService(fakeUserRepository);
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        graduationuserFacade = new GraduationuserFacade(graduationUserQueryService,graduationUserCommandService,userQueryService);

        User user = fakeUserRepository.save(User.builder()
            .id("202411345")
            .password(passwordEncoder.encode("password1234"))
            .name("홍길동")
            .email("test@kyonggi.ac.kr")
            .phone("010-1234-5678")
            .major(CSE)
            .build());

        graduationUser = fakeGraduatoinUserRepository.save(GraduationUser.builder()
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
    @DisplayName("selectGraduationType은 Grduation User의 졸업 방식을 선택한다.")
    public void Success() {
        //given
        Long graduatoinUserId = 1L;
        GraduationType graduationType = GraduationType.CERTIFICATE;

        //when
        graduationuserFacade.selectGraduationType(graduatoinUserId, graduationType);

        //then
        GraduationUser savedGraduationUser = fakeGraduatoinUserRepository.findByIdAndDeletedAtIsNull(graduatoinUserId).get();
        assertEquals(graduationType, savedGraduationUser.getGraduationType());
    }
}
