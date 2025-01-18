package lab.application;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import kgu.developers.domain.lab.application.query.LabQueryService;
import kgu.developers.domain.lab.domain.Lab;
import kgu.developers.domain.lab.exception.LabNotFoundException;
import mock.repository.FakeLabRepository;

public class LabQueryServiceTest {
	private LabQueryService labQueryService;

	private static final int TARGET_LAB_COUNT = 2;
	private static final Long TARGET_LAB_ID = 1L;
	private static final Long NOT_EXIST_LAB_ID = 3L;

	@BeforeEach
	public void init() {
		FakeLabRepository fakeLabRepository = new FakeLabRepository();
		labQueryService = new LabQueryService(fakeLabRepository);
		saveTestLabs(fakeLabRepository);
	}

	private static void saveTestLabs(FakeLabRepository fakeLabRepository) {
		fakeLabRepository.save(
			Lab.create("인공지능 연구실", "8502, 8503", "http://ailab.kyonggi.ac.kr", "김인철")
		);
		fakeLabRepository.save(
			Lab.create("알고리즘 연구실", "8504", "http://algeo.kyonggi.ac.kr/", "배상원")
		);
	}

	@Test
	@DisplayName("getLabsByName은 Lab 리스트를 조회할 수 있다")
	public void getLabsByName_Success() {
		// given
		// when
		List<Lab> LabList = labQueryService.getLabsByName();

		// then
		assertEquals(TARGET_LAB_COUNT, LabList.size());
		assertEquals("알고리즘 연구실", LabList.get(0).getName());
		assertEquals("인공지능 연구실", LabList.get(1).getName());
	}

	@Test
	@DisplayName("getById는 존재하는 ID로 조회시 해당 Lab을 반환한다")
	public void getById_Success() {
		// given
		// when
		Lab result = labQueryService.getById(TARGET_LAB_ID);

		// then
		assertEquals("인공지능 연구실", result.getName());
		assertEquals("8502, 8503", result.getLoc());
		assertEquals("http://ailab.kyonggi.ac.kr", result.getSite());
		assertEquals("김인철", result.getAdvisor());
	}

	@Test
	@DisplayName("getById로 존재하지 않는 ID로 조회시 LabNotFoundException을 발생시킨다")
	public void getById_Throws_LabNotFoundException() {
		// given
		// when
		// then
		assertThatThrownBy(() -> labQueryService.getById(NOT_EXIST_LAB_ID))
			.isInstanceOf(LabNotFoundException.class);
	}
}
