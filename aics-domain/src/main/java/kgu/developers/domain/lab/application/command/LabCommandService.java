package kgu.developers.domain.lab.application.command;

import org.springframework.stereotype.Service;

import kgu.developers.domain.lab.domain.Lab;
import kgu.developers.domain.lab.domain.LabRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class LabCommandService {
	private final LabRepository labRepository;

	public Long createLab(Long fileId, String name, String location, String site, String advisor) {
		Lab lab = Lab.create(name, location, site, advisor, fileId);
		return labRepository.save(lab).getId();
	}

	public void updateLab(Lab lab, String name, String loc, String site, String advisor, Long fileId) {
		lab.updateName(name);
		lab.updateLoc(loc);
		lab.updateSite(site);
		lab.updateAdvisor(advisor);
		lab.updateFileId(fileId);
	}

	public void deleteLabById(Long id) {
		labRepository.deleteById(id);
	}
}
