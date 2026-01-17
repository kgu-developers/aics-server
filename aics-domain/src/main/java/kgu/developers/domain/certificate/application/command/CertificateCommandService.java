package kgu.developers.domain.certificate.application.command;

import kgu.developers.domain.certificate.domain.Certificate;
import kgu.developers.domain.certificate.domain.CertificateRepository;
import kgu.developers.domain.certificate.exception.CertificateSubmissionTypeMismatchException;
import kgu.developers.domain.certificate.exception.CertificateNotFoundException;
import kgu.developers.domain.certificate.exception.CertificateNotInSubmissionPeriodException;
import kgu.developers.domain.file.application.command.FileCommandService;
import kgu.developers.domain.file.domain.FileDomain;
import kgu.developers.domain.file.infrastructure.repository.FileStorageService;
import kgu.developers.domain.graduationUser.application.query.GraduationUserQueryService;
import kgu.developers.domain.graduationUser.domain.GraduationType;
import kgu.developers.domain.graduationUser.domain.GraduationUser;
import kgu.developers.domain.schedule.application.query.ScheduleQueryService;
import kgu.developers.domain.schedule.domain.Schedule;
import kgu.developers.domain.schedule.domain.SubmissionType;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDateTime;

@Service
@Transactional
@RequiredArgsConstructor
public class CertificateCommandService {
	private final CertificateRepository certificateRepository;
	private final FileStorageService fileStorageService;
	private final FileCommandService fileCommandService;
	private final ScheduleQueryService scheduleQueryService;
	private final GraduationUserQueryService graduationUserQueryService;

	public Long submitCertificate(MultipartFile file) {
		Schedule schedule = scheduleQueryService.getBySubmissionType(SubmissionType.CERTIFICATE);

		GraduationUser graduationUser = graduationUserQueryService.me();

		if(graduationUser.getGraduationType() != GraduationType.CERTIFICATE) {
			throw new CertificateSubmissionTypeMismatchException();
		}

		LocalDateTime referenceTime = LocalDateTime.now();

		if(!schedule.isInProgress(referenceTime)) {
			throw new CertificateNotInSubmissionPeriodException();
		}

		String storedPath = fileStorageService.store(file, FileDomain.CERTIFICATE);
		Long fileId = fileCommandService.saveFile(file, storedPath).getId();

		Certificate certificate = Certificate.create(schedule.getId(), fileId);
		return certificateRepository.save(certificate);
	}

    public boolean approve(Long certificateId) {
		Certificate certificate = certificateRepository.findByIdAndDeletedAtIsNull(certificateId)
				.orElseThrow(CertificateNotFoundException::new);

		if (certificate.isApproved()) return false;
		certificate.approve();
		certificateRepository.save(certificate);
		return true;
    }
}
