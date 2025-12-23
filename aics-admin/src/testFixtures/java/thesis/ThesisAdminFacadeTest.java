package thesis;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import kgu.developers.admin.thesis.application.ThesisAdminFacade;
import kgu.developers.admin.thesis.presentation.response.ThesisDetailResponse;
import kgu.developers.domain.file.application.query.FileQueryService;
import kgu.developers.domain.thesis.application.query.ThesisQueryService;
import kgu.developers.domain.thesis.domain.Thesis;
import kgu.developers.domain.thesis.exception.ThesisNotFoundException;
import mock.repository.FakeFileRepository;
import mock.repository.FakeThesisRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class ThesisAdminFacadeTest {
    private ThesisAdminFacade thesisAdminFacade;
    private FakeThesisRepository fakeThesisRepository;

    @BeforeEach
    void init(){
        fakeThesisRepository = new FakeThesisRepository();
        thesisAdminFacade = new ThesisAdminFacade(
                new ThesisQueryService(fakeThesisRepository),
                new FileQueryService(new FakeFileRepository())
        );
        fakeThesisRepository.save(Thesis.of(1L,3L,null,false,null,null,null));
    }

    @Test
    @DisplayName("getById는 논문 정보를 반환한다")
    void getById(){
        ThesisDetailResponse response = thesisAdminFacade.getById(1L);

        assertEquals(1L,response.id());
        assertEquals(3L, response.scheduleId());
        assertEquals(false, response.approval());
        assertEquals(null, response.thesisFile());
    }
    @Test
    @DisplayName("없는 id 조회 시 ThesisNotFoundException")
    void getById_notFound() {
        assertThatThrownBy(() -> thesisAdminFacade.getById(999L))
                .isInstanceOf(ThesisNotFoundException.class);
    }

}
