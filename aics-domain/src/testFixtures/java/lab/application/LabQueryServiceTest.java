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
import mock.FakeLabRepository;

public class LabQueryServiceTest {
	private LabQueryService labQueryService;

	@BeforeEach
	public void init() {
		FakeLabRepository fakeLabRepository = new FakeLabRepository();
		this.labQueryService = new LabQueryService(fakeLabRepository);

		fakeLabRepository.save(Lab.builder()
			.name("Lab A")
			.loc("8500")
			.site("http://lab1.kyonggi.ac.kr")
			.advisor("김교수")
			.build()
		);

		fakeLabRepository.save(Lab.builder()
			.name("Lab B")
			.loc("8520")
			.site("https://lab2.kyonggi.ac.kr")
			.advisor("이교수")
			.build()
		);
	}

	@Test
	@DisplayName("getLabs은 Lab 리스트를 조회할 수 있다")
	public void getLabs_Success() {
		// given
		// when
		List<Lab> labs = labQueryService.getLabsByName();

		// then
		assertEquals(2, labs.size());
		assertEquals("Lab A", labs.get(0).getName());
		assertEquals("Lab B", labs.get(1).getName());
	}

	@Test
	@DisplayName("getById로 존재하지 않는 ID로 조회시 LabNotFoundException을 발생시킨다")
	public void getById_Throws_LabNotFoundException() {
		// given
		Long id = 0L;

		// when
		// then
		assertThatThrownBy(
			() -> labQueryService.getById(id)
		).isInstanceOf(LabNotFoundException.class);
	}
}
