package schedule.application;

import kgu.developers.domain.schedule.application.command.ScheduleCommandService;
import kgu.developers.domain.schedule.domain.Schedule;
import kgu.developers.domain.schedule.domain.SubmissionType;
import kgu.developers.domain.schedule.exception.DuplicateScheduleTypeException;
import kgu.developers.domain.schedule.exception.ScheduleNotFoundException;
import mock.repository.FakeScheduleRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

public class ScheduleCommandServiceTest {
    private ScheduleCommandService scheduleCommandService;
    private FakeScheduleRepository fakeScheduleRepository;

    private final LocalDateTime startDate = LocalDateTime.of(2024,2,1,9,0);
    private final LocalDateTime endDate = startDate.plusDays(7);

    @BeforeEach
    void init(){
        fakeScheduleRepository = new FakeScheduleRepository();
        scheduleCommandService = new ScheduleCommandService(fakeScheduleRepository);
    }

    @Test
    @DisplayName("createSchedule은 일정을 생성하고 ID를 반환한다.")
    void createSchedule_Success(){
        //given
        Long id = scheduleCommandService.createSchedule(
                SubmissionType.SUBMITTED,
                "신청 일정 본문",
                startDate,
                endDate
        );
        //when
        Schedule saved = fakeScheduleRepository.findById(id).orElseThrow();

        //then
        assertEquals(1L,id);
        assertEquals("신청 일정 본문", saved.getContent());
    }

    @Test
    @DisplayName("같은 SubmissionType으로 createSchedule을 호출하면 예외가 발생한다.")
    void createSchedule_DuplicateSubmissionType(){
        //given
        fakeScheduleRepository.save(
                Schedule.create(SubmissionType.SUBMITTED,"내용",startDate,endDate)
        );
        //when & then
        assertThrows(DuplicateScheduleTypeException.class, () ->
                scheduleCommandService.createSchedule(
                        SubmissionType.SUBMITTED,
                        "다른 내용",
                        startDate.plusDays(1),
                        endDate.plusDays(1)
                )
        );
    }

    @Test
    @DisplayName("updateSchedule은 기간을 갱신한다.")
    void updateSchedule_Success(){
        //given
        Schedule schedule = fakeScheduleRepository.save(
                Schedule.create(SubmissionType.SUBMITTED, "접수",  startDate, endDate)
        );
        scheduleCommandService.updateSchedule(
                schedule,
                startDate.plusWeeks(1),
                endDate.plusWeeks(1)
        );
        //when
        Schedule updated = fakeScheduleRepository.findById(schedule.getId()).orElseThrow();

        //then
        assertEquals(startDate.plusWeeks(1), updated.getStartDate());

    }


    @Test
    @DisplayName("updateScheduleContent는 내용을 수정한다")
    void updateScheduleContent_Success() {
        //given
        Schedule schedule = fakeScheduleRepository.save(
                Schedule.create(SubmissionType.SUBMITTED, "내용", startDate, endDate)
        );
        //when
        scheduleCommandService.updateScheduleContent(schedule, "콘텐츠 업데이트");
        //then
        Schedule updated = fakeScheduleRepository.findById(schedule.getId()).orElseThrow();
        assertEquals("콘텐츠 업데이트", updated.getContent());
    }

    @Test
    @DisplayName("deleteSchedule은 일정을 삭제한다")
    void deleteSchedule_Success() {
        //given
        Schedule schedule = fakeScheduleRepository.save(
                Schedule.create(SubmissionType.SUBMITTED,  "내용", startDate, endDate)
        );
        //when
        scheduleCommandService.deleteSchedule(schedule.getId());
        //then
        assertTrue(fakeScheduleRepository.findById(schedule.getId()).isEmpty());
    }

    @Test
    @DisplayName("존재하지 않는 ID로 deleteSchedule을 호출하면 예외가 발생한다")
    void deleteSchedule_NotFound() {
        assertThrows(ScheduleNotFoundException.class, () -> scheduleCommandService.deleteSchedule(999L));
    }


}
