package lab.application;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import kgu.developers.api.lab.application.LabService;
import kgu.developers.api.lab.presentation.request.LabRequest;
import kgu.developers.api.lab.presentation.response.LabPersistResponse;
import kgu.developers.domain.lab.domain.Lab;
import mock.FakeLabRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class LabServiceTest {
	private LabService labService;

	@BeforeEach
	public void init() {
		FakeLabRepository fakeLabRepository = new FakeLabRepository();
		this.labService = new LabService(fakeLabRepository);

		fakeLabRepository.save(Lab.builder()
			.id(1L)
			.name("Lab A")
			.loc("8500")
			.site("http://lab1.kyonggi.ac.kr")
			.build()
		);

		fakeLabRepository.save(Lab.builder()
			.id(2L)
			.name("Lab B")
			.loc("8520")
			.site("https://lab2.kyonggi.ac.kr")
			.build()
		);
	}

	@Test
	@DisplayName("createLab은 게시글을 생성할 수 있다")
	public void createLab_Success() {
		//given
		LabRequest request = LabRequest.builder()
			.name("Lab C")
			.loc("제2공학관 200")
			.site("http://research.kyonggi.ac.kr")
			.build();

		//when
		LabPersistResponse response = labService.createLab(request);

		//then
		assertNotNull(response);
		assertEquals(3, response.id());
	}

	@Test
	@DisplayName("getLabs은 게시글 리스트를 조회할 수 있다")
	public void getLabs_Success() {
		//given


		//when


		//then

	}

	@Test
	@DisplayName("updateLab은 게시글을 수정할 수 있다")
	public void updateLab_Success() {
		//given


		//when


		//then

	}

	@Test
	@DisplayName("deleteLab은 게시글을 삭제할 수 있다")
	public void deleteLab_Success() {
		//given


		//when


		//then

	}

	@Test
	@DisplayName("getById는 게시글을 조회할 수 있다")
	public void getById_Success() {
		//given


		//when


		//then

	}

	@Test
	@DisplayName("getById로 존재하지 않는 ID로 조회시 LabNotFoundException을 발생시킨다")
	public void getById_Throws_LabNotFoundException() {
		//given


		//when


		//then

	}


}
