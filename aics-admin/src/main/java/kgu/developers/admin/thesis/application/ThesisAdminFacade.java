package kgu.developers.admin.thesis.application;

import kgu.developers.admin.thesis.presentation.response.ThesisDetailResponse;
import kgu.developers.domain.file.application.query.FileQueryService;
import kgu.developers.domain.thesis.application.query.ThesisQueryService;
import kgu.developers.domain.thesis.domain.Thesis;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@Transactional
@RequiredArgsConstructor
public class ThesisAdminFacade {

    private final ThesisQueryService thesisQueryService;
    private final FileQueryService fileQueryService;

    public ThesisDetailResponse getById(Long id){
        Thesis thesis = thesisQueryService.getById(id);
        String physicalPath = thesis.getThesisFileId() != null
                ? fileQueryService.getFilePhysicalPath(thesis.getThesisFileId())
                : null;
        return ThesisDetailResponse.from(thesis, physicalPath);
    }
}
