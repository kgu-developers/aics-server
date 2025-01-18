package lab.application;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import kgu.developers.api.lab.application.LabFacade;
import kgu.developers.api.lab.presentation.response.LabDetailResponse;
import kgu.developers.api.lab.presentation.response.LabListResponse;
import kgu.developers.domain.lab.application.query.LabQueryService;
import kgu.developers.domain.lab.domain.Lab;
import mock.repository.FakeLabRepository;

public class LabFacadeTest {
	private LabFacade labFacade;

	@BeforeEach
	public void init() {
		FakeLabRepository fakeLabRepository = new FakeLabRepository();
		labFacade = new LabFacade(
			new LabQueryService(fakeLabRepository)
		);

		fakeLabRepository.save(
			Lab.create(
				"인공지능 연구실",
				"8502, 8503",
				"http://ailab.kyonggi.ac.kr",
				"김인철"
			)
		);

		fakeLabRepository.save(
			Lab.create(
				"알고리즘 연구실",
				"8504",
				"http://algeo.kyonggi.ac.kr/",
				"배상원"
			)
		);
	}

	@Test
	@DisplayName("getLabs 메서드는 Lab 리스트를 최근 추가순으로 반환한다")
	public void getLabs_Success() {
		// when
		LabListResponse response = labFacade.getLabs();

		// then
		List<LabDetailResponse> labs = response.contents();
		assertEquals(2, labs.size());

		LabDetailResponse lab1 = labs.get(0);
		assertEquals("알고리즘 연구실", lab1.name());
		assertEquals("8504", lab1.loc());
		assertEquals("http://algeo.kyonggi.ac.kr/", lab1.site());
		assertEquals("배상원", lab1.advisor());

		LabDetailResponse lab2 = labs.get(1);
		assertEquals("인공지능 연구실", lab2.name());
		assertEquals("8502, 8503", lab2.loc());
		assertEquals("http://ailab.kyonggi.ac.kr", lab2.site());
		assertEquals("김인철", lab2.advisor());
	}
}
