package kgu.developers.domain.lab.application.command;

import org.springframework.stereotype.Service;

import kgu.developers.domain.lab.domain.Lab;
import kgu.developers.domain.lab.domain.LabRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class LabCommandService {
	private final LabRepository labRepository;

	public Long createLab(String name, String location, String site, String professor) {
		Lab lab = Lab.create(name, location, site, professor);
		return labRepository.save(lab).getId();
	}

	public void updateLab(Lab lab, String name, String location, String site, String professor) {
		lab.updateName(name);
		lab.updateLocation(location);
		lab.updateSite(site);
		lab.updateProfessor(professor);
	}

	public void deleteLabById(Long id) {
		labRepository.deleteById(id);
	}
}
