package certificate.application;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import kgu.developers.admin.certificate.application.CertificateAdminFacade;
import kgu.developers.admin.certificate.presentation.response.CertificateDetailResponse;
import kgu.developers.domain.certificate.application.query.CertificateQueryService;
import kgu.developers.domain.certificate.domain.Certificate;
import kgu.developers.domain.certificate.exception.CertificateNotFoundException;
import kgu.developers.domain.file.application.query.FileQueryService;
import mock.repository.FakeCertificateRepository;
import mock.repository.FakeFileRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class CertificateAdminFacadeTest {

    private CertificateAdminFacade certificateAdminFacade;
    private FakeCertificateRepository fakeCertificateRepository;

    @BeforeEach
    void init(){
        fakeCertificateRepository = new FakeCertificateRepository();
        certificateAdminFacade= new CertificateAdminFacade(
                new CertificateQueryService(fakeCertificateRepository),
                new FileQueryService(new FakeFileRepository()));
        fakeCertificateRepository.save(Certificate.of(1L,3L,null,true,null,null,null));
    }

    @Test
    @DisplayName("getById는 자격증 정보를 반환한다.")
    void getById_success(){
        CertificateDetailResponse response = certificateAdminFacade.getById(1L);

        assertEquals(1L,response.id());
        assertEquals(3L,response.scheduleId());
        assertEquals(true,response.approval());
        assertEquals(null,response.certificateFile());
    }
    @Test
    @DisplayName("없는 id 조회 시 CertificateNotFoundException")
    void getById_notFound() {
        assertThatThrownBy(() -> certificateAdminFacade.getById(999L))
                .isInstanceOf(CertificateNotFoundException.class);
    }


}
