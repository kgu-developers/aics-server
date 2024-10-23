package kgu.developers.core.domain.file.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import kgu.developers.core.common.domain.BaseTimeEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import static jakarta.persistence.GenerationType.IDENTITY;
import static lombok.AccessLevel.PROTECTED;

@Entity
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = PROTECTED)
public class FileEntity extends BaseTimeEntity {
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "file_id")
	private Long id;

	// 여기는 사용자가 입력한 파일명을 저장
	@Column(nullable = false, length = 255)
	private String logicalName;

	// 파일위치 + unigue하게 생성한 파일명
	// 형식: /도메인/년/월/일/파일명.확장자 ex) "posts/24/10/23/홍길동_졸업사정_3982397194.hwp"
	@Column(nullable = false, length = 255, unique = true)
	private String physicalPath;

	public static FileEntity create(String logicalName, String physicalPath) {
		// TODO: UUID or 시간을 넣어서 physicalPath를 unique로 만들기

		return FileEntity.builder()
			.logicalName(logicalName)
			.physicalPath(physicalPath)
			.build();
	}

}
