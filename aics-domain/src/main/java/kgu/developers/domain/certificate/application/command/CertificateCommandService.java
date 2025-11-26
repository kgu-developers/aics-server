package kgu.developers.domain.certificate.application.command;

import kgu.developers.domain.certificate.exception.CertificateNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import kgu.developers.domain.certificate.domain.Certificate;
import kgu.developers.domain.certificate.domain.CertificateRepository;
import kgu.developers.domain.file.application.command.FileCommandService;
import kgu.developers.domain.file.domain.FileDomain;
import kgu.developers.domain.file.infrastructure.repository.FileStorageService;
import kgu.developers.domain.schedule.application.query.ScheduleQueryService;
import lombok.RequiredArgsConstructor;

@Service
@Transactional
@RequiredArgsConstructor
public class CertificateCommandService {
	private final CertificateRepository certificateRepository;
	private final FileStorageService fileStorageService;
	private final FileCommandService fileCommandService;
	private final ScheduleQueryService scheduleQueryService;

	public Long submitCertificate(MultipartFile file, Long scheduleId) {
		String storedPath = fileStorageService.store(file, FileDomain.CERTIFICATE);
		Long fileId = fileCommandService.saveFile(file, storedPath).getId();
		scheduleQueryService.checkExistsOrThrow(scheduleId);

		Certificate certificate = Certificate.create(scheduleId, fileId);
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
