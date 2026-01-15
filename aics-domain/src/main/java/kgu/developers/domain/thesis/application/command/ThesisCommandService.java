package kgu.developers.domain.thesis.application.command;

import jakarta.transaction.Transactional;
import kgu.developers.domain.file.application.command.FileCommandService;
import kgu.developers.domain.file.domain.FileDomain;
import kgu.developers.domain.file.infrastructure.repository.FileStorageService;
import kgu.developers.domain.graduationUser.application.query.GraduationUserQueryService;
import kgu.developers.domain.graduationUser.domain.GraduationType;
import kgu.developers.domain.graduationUser.domain.GraduationUser;
import kgu.developers.domain.schedule.application.query.ScheduleQueryService;
import kgu.developers.domain.schedule.domain.Schedule;
import kgu.developers.domain.schedule.domain.SubmissionType;
import kgu.developers.domain.thesis.domain.Thesis;
import kgu.developers.domain.thesis.domain.ThesisRepository;
import kgu.developers.domain.thesis.exception.ThesisInvalidGraduationTypeException;
import kgu.developers.domain.thesis.exception.ThesisInvalidSubmissionTypeException;
import kgu.developers.domain.thesis.exception.ThesisNotFoundException;
import kgu.developers.domain.thesis.exception.ThesisNotInSubmissionPeriodException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDateTime;

@Service
@Transactional
@RequiredArgsConstructor
public class ThesisCommandService {
	private final ThesisRepository thesisRepository;
	private final FileStorageService fileStorageService;
	private final FileCommandService fileCommandService;
	private final ScheduleQueryService scheduleQueryService;
	private final GraduationUserQueryService graduationUserQueryService;

	public Long submitThesis(MultipartFile file, SubmissionType thesisType) {

		if(thesisType != SubmissionType.MIDTHESIS && thesisType != SubmissionType.FINALTHESIS) {
			throw new ThesisInvalidSubmissionTypeException();
		}

		Schedule schedule = scheduleQueryService.getBySubmissionType(thesisType);
		LocalDateTime referenceTime = LocalDateTime.now();

		GraduationUser graduationUser = graduationUserQueryService.me();

		if(graduationUser.getGraduationType() != GraduationType.THESIS) {
			throw new ThesisInvalidGraduationTypeException();
		}

		if(!schedule.isInProgress(referenceTime)) {
			throw new ThesisNotInSubmissionPeriodException();
		}

		String storedPath = fileStorageService.store(file, FileDomain.THESIS);
		Long fileId = fileCommandService.saveFile(file, storedPath).getId();

		Thesis thesis = Thesis.create(schedule.getId(), fileId);
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
