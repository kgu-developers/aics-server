package kgu.developers.core.domain.major.domain;

import jakarta.persistence.*;
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
@NoArgsConstructor(access = PROTECTED)
@AllArgsConstructor
public class Major extends BaseTimeEntity {
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "major_id")
	private Long id;

	@Column(nullable = false, length = 10)
	private String majorName;

	public static Major create(String majorName) {
		return Major.builder()
			.majorName(majorName)
			.build();
	}
}
