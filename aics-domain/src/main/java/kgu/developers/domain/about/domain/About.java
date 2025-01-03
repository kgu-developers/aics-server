package kgu.developers.domain.about.domain;

import static jakarta.persistence.EnumType.STRING;
import static jakarta.persistence.GenerationType.IDENTITY;
import static kgu.developers.domain.about.domain.MainCategory.DEPT_INTRO;
import static kgu.developers.domain.about.domain.MainCategory.EDU_ACTIVITIES;
import static kgu.developers.domain.about.domain.SubCategory.CLUB_INTRO;
import static kgu.developers.domain.about.domain.SubCategory.CURRICULUM;
import static kgu.developers.domain.about.domain.SubCategory.EDU_ENVIRONMENT;
import static kgu.developers.domain.about.domain.SubCategory.EDU_OBJECTIVES;
import static kgu.developers.domain.about.domain.SubCategory.HISTORY;
import static kgu.developers.domain.about.domain.SubCategory.LEARNING_ACTIVITIES;
import static lombok.AccessLevel.PROTECTED;

import java.util.EnumMap;
import java.util.Map;
import java.util.Set;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import kgu.developers.common.domain.BaseTimeEntity;
import kgu.developers.domain.about.exception.CategoryNotMatchException;
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

	@Column(nullable = false)
	@Enumerated(STRING)
	private MainCategory mainCategory;

	@Column(nullable = false)
	@Enumerated(STRING)
	private SubCategory subCategory;

	private String detailCategory;

	@Column(nullable = false, columnDefinition = "text")
	private String content;

	public static About create(MainCategory mainCategory, SubCategory subCategory, String detailCategory, String content) {
		return About.builder()
			.mainCategory(mainCategory)
			.subCategory(subCategory)
			.detailCategory(detailCategory)
			.content(content)
			.build();
	}

	public void updateContent(String content) {
		this.content = content;
	}

	private static final Map<MainCategory, Set<SubCategory>> CATEGORY_MAP = new EnumMap<>(MainCategory.class);

	static {
		CATEGORY_MAP.put(DEPT_INTRO, Set.of(SubCategory.DEPT_INTRO, HISTORY, EDU_ENVIRONMENT, EDU_OBJECTIVES));
		CATEGORY_MAP.put(EDU_ACTIVITIES, Set.of(CURRICULUM, LEARNING_ACTIVITIES, CLUB_INTRO));
	}

	public static void categoryMatchCheck(MainCategory mainCategory, SubCategory subCategory) {
		if (!CATEGORY_MAP.getOrDefault(mainCategory, Set.of()).contains(subCategory)) {
			throw new CategoryNotMatchException();
		}
	}
}
