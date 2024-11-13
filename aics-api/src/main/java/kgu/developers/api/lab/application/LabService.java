package kgu.developers.api.lab.application;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import kgu.developers.api.lab.presentation.exception.LabNotFoundException;
import kgu.developers.api.lab.presentation.request.LabRequest;
import kgu.developers.api.lab.presentation.response.LabListResponse;
import kgu.developers.api.lab.presentation.response.LabPersistResponse;
import kgu.developers.domain.lab.domain.Lab;
import kgu.developers.domain.lab.domain.LabRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class LabService {
	private final LabRepository labRepository;

	@Transactional
	public LabPersistResponse createLab(LabRequest request) {
		Lab lab = Lab.create(request.name(), request.loc(), request.site());
		labRepository.save(lab);

		return LabPersistResponse.of(lab.getId());
	}

	@Transactional(readOnly = true)
	public LabListResponse getLabs() {
		List<Lab> labs = labRepository.findAllByOrderByName();
		return LabListResponse.from(labs);
	}

	@Transactional
	public void updateLab(Long id, LabRequest request) {
		Lab lab = getById(id);

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
