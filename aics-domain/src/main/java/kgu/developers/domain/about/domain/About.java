package kgu.developers.domain.about.domain;

import static jakarta.persistence.GenerationType.IDENTITY;
import static lombok.AccessLevel.PROTECTED;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import kgu.developers.common.domain.BaseTimeEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = PROTECTED)
public class About extends BaseTimeEntity {
	@Id
	@GeneratedValue(strategy = IDENTITY)
	private Long id;

	//TODO: 각 enum에 데이터 추가 후 주석 제거
	// @Enumerated(STRING)
	// private MainCategory mainCategory;
	//
	// @Enumerated(STRING)
	// private SubCategory subCategory;
	//
	// @Enumerated(STRING)
	// private DetailCategory detailCategory;

	@Column(nullable = false, columnDefinition = "text")
	private String content;
}
