package kgu.developers.domain.thesis.application.command;

import jakarta.transaction.Transactional;
import kgu.developers.domain.file.application.command.FileCommandService;
import kgu.developers.domain.file.domain.FileDomain;
import kgu.developers.domain.file.infrastructure.repository.FileStorageService;
import kgu.developers.domain.schedule.application.query.ScheduleQueryService;
import kgu.developers.domain.thesis.domain.Thesis;
import kgu.developers.domain.thesis.domain.ThesisRepository;
import kgu.developers.domain.thesis.exception.ThesisNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
@Transactional
@RequiredArgsConstructor
public class ThesisCommandService {
	private final ThesisRepository thesisRepository;
	private final FileStorageService fileStorageService;
	private final FileCommandService fileCommandService;
	private final ScheduleQueryService scheduleQueryService;

	public Long submitThesis(MultipartFile file, Long scheduleId) {
		String storedPath = fileStorageService.store(file, FileDomain.THESIS);
		Long fileId = fileCommandService.saveFile(file, storedPath).getId();
		scheduleQueryService.checkExistsOrThrow(scheduleId);

		Thesis thesis = Thesis.create(scheduleId, fileId);
		return thesisRepository.save(thesis);
	}

    public boolean approve(Long thesisId) {
		Thesis thesis = thesisRepository.findByIdAndDeletedAtIsNull(thesisId)
				.orElseThrow(ThesisNotFoundException::new);

		if (thesis.isApproved()) return false;
		thesis.approve();
		thesisRepository.save(thesis);
		return true;
    }
}
