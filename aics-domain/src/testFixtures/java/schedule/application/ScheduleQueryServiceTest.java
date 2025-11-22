package schedule.application;

import kgu.developers.domain.schedule.application.query.ScheduleQueryService;
import kgu.developers.domain.schedule.domain.Schedule;
import kgu.developers.domain.schedule.domain.SubmissionType;
import kgu.developers.domain.schedule.exception.ScheduleNotFoundException;
import mock.repository.FakeScheduleRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.time.LocalDateTime;
import java.util.List;

public class ScheduleQueryServiceTest {
    private ScheduleQueryService scheduleQueryService;
    private FakeScheduleRepository fakeScheduleRepository;
    private static final LocalDateTime baseStart = LocalDateTime.of(2024, 3, 1, 9, 0);

    @BeforeEach
    void init(){
        fakeScheduleRepository = new FakeScheduleRepository();
        scheduleQueryService = new ScheduleQueryService(fakeScheduleRepository);

        fakeScheduleRepository.save(
                Schedule.create(
                        SubmissionType.SUBMITTED,
                        "신청 일정",
                        "신청 일정 본문",
                        baseStart,
                        baseStart.plusDays(3)
                )
        );

        fakeScheduleRepository.save(
                Schedule.create(
                        SubmissionType.MIDTHESIS,
                        "중간 논문",
                        "중간 논문 설명",
                        baseStart.plusDays(7),
                        baseStart.plusDays(10)
                )
        );
    }

    @Test
    @DisplayName("getAllScheduleManagements는 전체 일정을 반환한다.")
    void getAllScheduleManagements_Success() {
        //given
        //when
        List<Schedule> result = scheduleQueryService.getAllScheduleManagements();
        //then
        assertEquals(2, result.size());
        assertEquals(SubmissionType.SUBMITTED, result.get(0).getSubmissionType());
        assertEquals(SubmissionType.MIDTHESIS, result.get(1).getSubmissionType());
    }

    @Test
    @DisplayName("getScheduleManagement는 ID로 일정을 조회한다")
    void getScheduleManagement_Success(){
        Schedule schedule = scheduleQueryService.getScheduleManagement(1L);

        assertEquals("신청 일정",schedule.getTitle());
        assertEquals(SubmissionType.SUBMITTED, schedule.getSubmissionType());
    }

    @Test
    @DisplayName("getScheduleManagement는 존재하지 않는 ID면 예외를 던집니다.")
    void getScheduleManagement_NotFound() {
        assertThrows(ScheduleNotFoundException.class, () -> scheduleQueryService.getScheduleManagement(99L));
    }

    @Test
    @DisplayName("getBySubmissionType은 제출타입으로 일정을 찾는다")
    void getBySubmissionType_Success() {
        Schedule schedule = scheduleQueryService.getBySubmissionType(SubmissionType.MIDTHESIS);

        assertEquals("중간 논문", schedule.getTitle());
    }

}
