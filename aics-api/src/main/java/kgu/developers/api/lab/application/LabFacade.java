package kgu.developers.api.lab.application;

import java.util.List;
import java.util.Objects;

import kgu.developers.domain.file.application.query.FileQueryService;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import kgu.developers.api.lab.presentation.response.LabListResponse;
import kgu.developers.domain.lab.application.query.LabQueryService;
import kgu.developers.domain.lab.domain.Lab;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class LabFacade {
	private final LabQueryService labQueryService;
	private final FileQueryService fileQueryService;

	public LabListResponse getLabs() {
		List<Lab> labs = labQueryService.getLabsByName();

		List<Long> fileIds = labs.stream()
				.map(Lab::getFileId)
				.filter(Objects::nonNull)
				.toList();


		return LabListResponse.from(labs,fileQueryService.findFileEntityMapByIds(fileIds));
	}
}
