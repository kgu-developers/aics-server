package kgu.developers.domain.schedulemanagement.application.query;

import kgu.developers.domain.schedulemanagement.domain.ScheduleManagement;
import kgu.developers.domain.schedulemanagement.domain.ScheduleManagementRepository;
import kgu.developers.domain.schedulemanagement.domain.SubmissionType;
import kgu.developers.domain.schedulemanagement.exception.ScheduleManagementNotFoundException
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ScheduleManagementQueryService {
    private final ScheduleManagementRepository scheduleManagementRepository;

    public List<ScheduleManagement> getAllScheduleManagements() {
        return scheduleManagementRepository.findAll();
    }

    public ScheduleManagement getScheduleManagement(Long id) {
        return scheduleManagementRepository
                .findById(id)
                .orElseThrow(ScheduleManagementNotFoundException::new);
    }
    public List<ScheduleManagement> findBySubmissionType(SubmissionType submissionType) {
        return scheduleManagementRepository.findBySubmissionType(submissionType);
    }
}
