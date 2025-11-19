package schedule.domain;

import kgu.developers.domain.schedule.domain.Schedule;
import kgu.developers.domain.schedule.domain.ScheduleStatus;
import kgu.developers.domain.schedule.domain.SubmissionType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;



import java.time.LocalDateTime;

public class ScheduleDomainTest {
    private Schedule schedule;
    private static final LocalDateTime startDate = LocalDateTime.of(2024,4,1,9,0) ;
    private static final LocalDateTime endDate = startDate.plusDays(5);

    @BeforeEach
    void init(){
        schedule  = Schedule.create(
                SubmissionType.SUBMITTED,
                "신청 일정",
                "신청 관련 설명",
                startDate,
                endDate
        );
    }

    @Test
    @DisplayName("Schedule 객체를 올바르게 생성할 수 있다.")
    void createSchedule_Success(){
        assertEquals(SubmissionType.SUBMITTED, schedule.getSubmissionType());
        assertEquals("신청 일정",schedule.getTitle());
        assertEquals("신청 관련 설명", schedule.getContent());
        assertEquals(startDate,schedule.getStartDate());
        assertEquals(endDate,schedule.getEndDate());
    }

    @Test
    @DisplayName("determineStatusAt은 대기, 진행 중, 종료 상태를 판별합니다.")
    void determinStatusAt_ReturnsExpectedStatus(){
        assertEquals(ScheduleStatus.PENDING, schedule.determineStatusAt(startDate.minusDays(1)));
        assertEquals(ScheduleStatus.IN_PROGRESS, schedule.determineStatusAt(startDate.plusHours(1)));
        assertEquals(ScheduleStatus.CLOSED, schedule.determineStatusAt(endDate.plusMinutes(1)));
    }

    @Test
    @DisplayName("update 메서드는 제출타입과 기간, 타이틀을 수정할 수 있다.")
    void updateScheduleFields_Success(){
        LocalDateTime newStart = startDate.plusDays(10);
        LocalDateTime newEnd = newStart.plusDays(3);

        schedule.updateSubmissionType(SubmissionType.MIDTHESIS);
        schedule.updateTitle("중간 점검");
        schedule.updateStartDate(newStart);
        schedule.updateEndDate(newEnd);

        assertEquals(SubmissionType.MIDTHESIS, schedule.getSubmissionType());
        assertEquals("중간 점검", schedule.getTitle());
        assertEquals(newStart, schedule.getStartDate());
        assertEquals(newEnd, schedule.getEndDate());

    }

    @Test
    @DisplayName("updateContent는 내용을 변경한다")
    void updateContent_Success() {
        schedule.updateContent("세부 내용 변경");

        assertEquals("세부 내용 변경", schedule.getContent());
    }

}
