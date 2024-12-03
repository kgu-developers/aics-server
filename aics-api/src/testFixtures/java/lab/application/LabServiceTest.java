package lab.application;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import kgu.developers.api.lab.application.LabService;
import kgu.developers.api.lab.presentation.exception.LabNotFoundException;
import kgu.developers.api.lab.presentation.request.LabRequest;
import kgu.developers.api.lab.presentation.response.LabDetailResponse;
import kgu.developers.api.lab.presentation.response.LabListResponse;
import kgu.developers.api.lab.presentation.response.LabPersistResponse;
import kgu.developers.domain.lab.domain.Lab;
import mock.FakeLabRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

public class LabServiceTest {
	private LabService labService;

	@BeforeEach
	public void init() {
		FakeLabRepository fakeLabRepository = new FakeLabRepository();
		this.labService = new LabService(fakeLabRepository);

		fakeLabRepository.save(Lab.builder()
			.name("Lab A")
			.loc("8500")
			.site("http://lab1.kyonggi.ac.kr")
			.build()
		);

		fakeLabRepository.save(Lab.builder()
			.name("Lab B")
			.loc("8520")
			.site("https://lab2.kyonggi.ac.kr")
			.build()
		);
	}

	@Test
	@DisplayName("createLab은 Lab을 생성할 수 있다")
	public void createLab_Success() {
		//given
		LabRequest request = LabRequest.builder()
			.name("Lab C")
			.loc("제2공학관 200")
			.site("http://lab3.kyonggi.ac.kr")
			.build();

		//when
		LabPersistResponse response = labService.createLab(request);

		//then
		assertNotNull(response);
		assertEquals(3, response.id());
	}

	@Test
	@DisplayName("getLabs은 Lab 리스트를 조회할 수 있다")
	public void getLabs_Success() {
		//given
		//when
		LabListResponse labs = labService.getLabs();
		List<LabDetailResponse> contents = labs.contents();

		//then
		assertEquals(2, contents.size());
		assertEquals("Lab A", contents.get(0).name());
		assertEquals("Lab B", contents.get(1).name());
	}

	@Test
	@DisplayName("getById로 존재하지 않는 ID로 조회시 LabNotFoundException을 발생시킨다")
	public void getById_Throws_LabNotFoundException() {
		//given
		Long id = 0L;

		//when - getById는 private 이기 때문에 delete를 이용해 접근
		//then
		assertThatThrownBy(
			() -> labService.deleteLab(id)
		).isInstanceOf(LabNotFoundException.class);
	}


	@Test
	@DisplayName("updateLab은 Lab을 수정할 수 있다")
	public void updateLab_Success() {
		//given
		Long id = 1L;
		String newName = "Lab C";
		String newLoc = "제2공학관 200";
		String newSite = "https://lab3.kyonggi.ac.kr";
		LabRequest request = LabRequest.builder()
			.name(newName)
			.loc(newLoc)
			.site(newSite)
			.build();

		//when
		labService.updateLab(id, request);
		List<LabDetailResponse> contents = labService.getLabs().contents();

		//then - 이름순이기 때문에 순서까지 바뀌어서 리턴
		assertEquals(newName, contents.get(1).name());
		assertEquals(newLoc, contents.get(1).loc());
		assertEquals(newSite, contents.get(1).site());
	}

	@Test
	@DisplayName("deleteLab은 Lab을 삭제할 수 있다")
	public void deleteLab_Success() {
		//given
		Long id = 1L;

		//when
		labService.deleteLab(id);
		List<LabDetailResponse> contents = labService.getLabs().contents();

		//then
		assertEquals(1, contents.size());
		assertEquals("Lab B", contents.get(0).name());
	}
}
