package schedule.application;

import static kgu.developers.domain.schedule.domain.SubmissionType.CERTIFICATE;
import static kgu.developers.domain.schedule.domain.SubmissionType.MIDTHESIS;
import static kgu.developers.domain.schedule.domain.SubmissionType.SUBMITTED;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDateTime;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import kgu.developers.admin.schedule.application.ScheduleAdminFacade;
import kgu.developers.admin.schedule.presentation.request.ScheduleContentUpdateRequest;
import kgu.developers.admin.schedule.presentation.request.ScheduleCreateRequest;
import kgu.developers.admin.schedule.presentation.request.ScheduleUpdateRequest;
import kgu.developers.admin.schedule.presentation.response.SchedulePersistResponse;
import kgu.developers.domain.schedule.application.command.ScheduleCommandService;
import kgu.developers.domain.schedule.application.query.ScheduleQueryService;
import kgu.developers.domain.schedule.domain.Schedule;
import kgu.developers.domain.schedule.domain.SubmissionType;
import kgu.developers.domain.schedule.exception.DuplicateScheduleTypeException;
import kgu.developers.domain.schedule.exception.ScheduleNotFoundException;
import mock.repository.FakeScheduleRepository;

public class ScheduleAdminFacadeTest {

	private static final LocalDateTime DEFAULT_START_DATE = LocalDateTime.of(2025, 1, 1, 0, 0);
	private static final LocalDateTime DEFAULT_END_DATE = LocalDateTime.of(2025, 2, 1, 0, 0);

	private ScheduleAdminFacade scheduleAdminFacade;
	private FakeScheduleRepository fakeScheduleRepository;
	private Long savedScheduleId;

	@BeforeEach
	public void init() {
		fakeScheduleRepository = new FakeScheduleRepository();
		scheduleAdminFacade = new ScheduleAdminFacade(
			new ScheduleCommandService(fakeScheduleRepository),
			new ScheduleQueryService(fakeScheduleRepository)
		);

		Schedule savedSchedule = fakeScheduleRepository.save(
			Schedule.create(
				SUBMITTED,
				"본문",
				DEFAULT_START_DATE,
				DEFAULT_END_DATE
			)
		);
		savedScheduleId = savedSchedule.getId();
	}

	@Test
	@DisplayName("createSchedule은 일정을 생성하고 식별자를 반환한다")
	void createSchedule_Success() {
		// given
		ScheduleCreateRequest request = ScheduleCreateRequest.builder()
			.submissionType(MIDTHESIS)
			.content("중간보고서 본문")
			.startDate(DEFAULT_START_DATE.plusMonths(1))
			.endDate(DEFAULT_END_DATE.plusMonths(1))
			.build();

		// when
		SchedulePersistResponse response = scheduleAdminFacade.createSchedule(request);

		// then
		assertEquals(2L, response.scheduleId());
		assertEquals(2, fakeScheduleRepository.findAll().size());
	}

	@Test
	@DisplayName("기존 제출 유형으로 생성하면 DuplicateScheduleTypeException을 발생시킨다")
	void createSchedule_DuplicatedSubmissionType_ThrowsException() {
		// given
		ScheduleCreateRequest request = ScheduleCreateRequest.builder()
			.submissionType(SUBMITTED)
			.content("본문")
			.startDate(DEFAULT_START_DATE)
			.endDate(DEFAULT_END_DATE)
			.build();

		// when & then
		assertThatThrownBy(() -> scheduleAdminFacade.createSchedule(request))
			.isInstanceOf(DuplicateScheduleTypeException.class);
	}

	@Test
	@DisplayName("updateSchedule은 제출 유형과 기간 정보를 수정한다")
	void updateSchedule_Success() {
		// given
		ScheduleUpdateRequest request = ScheduleUpdateRequest.builder()
			.startDate(DEFAULT_START_DATE.plusDays(3))
			.endDate(DEFAULT_END_DATE.plusDays(5))
			.build();

		// when
		scheduleAdminFacade.updateSchedule(savedScheduleId, request);

		// then
		Schedule updated = fakeScheduleRepository.findById(savedScheduleId).orElseThrow();
		assertEquals(DEFAULT_START_DATE.plusDays(3), updated.getStartDate());
		assertEquals(DEFAULT_END_DATE.plusDays(5), updated.getEndDate());
	}

	@Test
	@DisplayName("존재하지 않는 id로 일정 수정 시 ScheduleNotFoundException을 발생시킨다")
	void updateSchedule_NotFound_ThrowsException() {
		// given
		ScheduleUpdateRequest request = ScheduleUpdateRequest.builder()
			.startDate(DEFAULT_START_DATE)
			.endDate(DEFAULT_END_DATE)
			.build();

		// when & then
		assertThatThrownBy(() -> scheduleAdminFacade.updateSchedule(999L, request))
			.isInstanceOf(ScheduleNotFoundException.class);
	}

	@Test
	@DisplayName("updateScheduleContent는 제출 유형 기준으로 본문만 수정한다")
	void updateScheduleContent_Success() {
		// given
		ScheduleContentUpdateRequest request = ScheduleContentUpdateRequest.builder()
			.content("본문 수정")
			.build();

		// when
		scheduleAdminFacade.updateScheduleContent(SUBMITTED, request);

		// then
		Schedule updated = fakeScheduleRepository.findById(savedScheduleId).orElseThrow();
		assertEquals("본문 수정", updated.getContent());
	}

	@Test
	@DisplayName("존재하지 않는 제출 유형으로 본문 수정 시 ScheduleNotFoundException을 발생시킨다")
	void updateScheduleContent_NotFound_ThrowsException() {
		// given
		ScheduleContentUpdateRequest request = ScheduleContentUpdateRequest.builder()
			.content("본문")
			.build();

		// when & then
		assertThatThrownBy(() -> scheduleAdminFacade.updateScheduleContent(MIDTHESIS, request))
			.isInstanceOf(ScheduleNotFoundException.class);
	}

	@Test
	@DisplayName("deleteSchedule은 일정을 삭제한다")
	void deleteSchedule_Success() {
		// when
		scheduleAdminFacade.deleteSchedule(savedScheduleId);

		// then
		assertTrue(fakeScheduleRepository.findById(savedScheduleId).isEmpty());
	}
}
