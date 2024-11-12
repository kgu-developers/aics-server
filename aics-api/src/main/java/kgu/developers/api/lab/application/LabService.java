package kgu.developers.api.lab.application;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import kgu.developers.api.lab.presentation.exception.LabNotFoundException;
import kgu.developers.api.lab.presentation.request.LabRequest;
import kgu.developers.api.lab.presentation.response.LabListResponse;
import kgu.developers.api.lab.presentation.response.LabPersistResponse;
import kgu.developers.api.priority.application.PriorityService;
import kgu.developers.domain.lab.domain.Lab;
import kgu.developers.domain.lab.domain.LabRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class LabService {
	private final LabRepository labRepository;
	private final PriorityService<Lab> priorityService;

	@Transactional
	public LabPersistResponse createLab(LabRequest request) {
		int adjustedPriority = priorityService.createAdjustPriority(Lab.class, request.priority());

		Lab lab = Lab.create(adjustedPriority, request.name(), request.loc(), request.site());
		labRepository.save(lab);

		return LabPersistResponse.of(lab.getId());
	}

	@Transactional(readOnly = true)
	public LabListResponse getLabs() {
		List<Lab> labs = labRepository.findByDeletedAtIsNullOrderByName();
		return LabListResponse.from(labs);
	}

	@Transactional
	public void updateLab(Long id, LabRequest request) {
		Lab lab = getById(id);

		if (!lab.isPriorityEqual(request.priority())) {
			int adjustedPriority = priorityService.updateAdjustPriority(Lab.class, lab.getPriority(),
				request.priority());
			lab.updatePriority(adjustedPriority);
		}

		lab.updateName(request.name());
		lab.updateLoc(request.loc());
		lab.updateSite(request.site());
	}

	@Transactional
	public void deleteLab(Long id) {
		Lab lab = getById(id);
		labRepository.delete(lab);
	}

	private Lab getById(Long id) {
		return labRepository.findById(id).orElseThrow(LabNotFoundException::new);
	}
}
