package kgu.developers.domain.lab.application.command;

import org.springframework.stereotype.Service;

import kgu.developers.domain.file.application.query.FileQueryService;
import kgu.developers.domain.file.domain.FileEntity;
import kgu.developers.domain.lab.domain.Lab;
import kgu.developers.domain.lab.domain.LabRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class LabCommandService {
	private final FileQueryService fileQueryService;
	private final LabRepository labRepository;

	public Long createLab(Long fileId, String name, String location, String site, String advisor) {
		FileEntity labImageFile = fileQueryService.getFileById(fileId);
		Lab lab = Lab.create(name, location, site, advisor, labImageFile);
		return labRepository.save(lab).getId();
	}

	public void updateLab(Lab lab, String name, String loc, String site, String advisor, Long fileId) {
		lab.updateName(name);
		lab.updateLoc(loc);
		lab.updateSite(site);
		lab.updateAdvisor(advisor);

		FileEntity file = null;
		if (fileId != null) {
			file = fileQueryService.getFileById(fileId);
		}

		lab.updateFile(file);
	}

	public void deleteLabById(Long id) {
		labRepository.deleteById(id);
	}
}
