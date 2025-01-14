package kgu.developers.admin.lab.application;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import kgu.developers.admin.lab.presentation.request.LabRequest;
import kgu.developers.admin.lab.presentation.response.LabPersistResponse;
import kgu.developers.domain.lab.application.command.LabCommandService;
import kgu.developers.domain.lab.application.query.LabQueryService;
import kgu.developers.domain.lab.domain.Lab;
import lombok.RequiredArgsConstructor;

@Component
@Transactional
@RequiredArgsConstructor
public class LabAdminFacade {
	private final LabCommandService labCommandService;
	private final LabQueryService labQueryService;

	public LabPersistResponse createLab(LabRequest request) {
		Long id = labCommandService.createLab(request.name(), request.loc(), request.site(), request.advisor());
		return LabPersistResponse.of(id);
	}

	public void updateLab(Long id, LabRequest request) {
		Lab lab = labQueryService.getById(id);
		labCommandService.updateLab(lab, request.name(), request.loc(), request.site(), request.advisor());
	}

	public void deleteLab(Long id) {
		labCommandService.deleteLabById(id);
	}
}
