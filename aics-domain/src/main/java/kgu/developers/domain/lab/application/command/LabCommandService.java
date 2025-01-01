package kgu.developers.domain.lab.application.command;

import org.springframework.stereotype.Service;

import kgu.developers.domain.lab.domain.Lab;
import kgu.developers.domain.lab.domain.LabRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class LabCommandService {
	private final LabRepository labRepository;

	public Long createLab(String name, String loc, String site) {
		Lab lab = Lab.create(name, loc, site);
		return labRepository.save(lab).getId();
	}

	public void updateLab(Lab lab, String name, String loc, String site) {
		lab.updateName(name);
		lab.updateLoc(loc);
		lab.updateSite(site);
	}

	public void deleteLabById(Long id) {
		labRepository.deleteById(id);
	}
}
