package kgu.developers.domain.about.domain;

import static jakarta.persistence.EnumType.STRING;
import static jakarta.persistence.GenerationType.IDENTITY;
import static lombok.AccessLevel.PROTECTED;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Enumerated;
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

	@Column(nullable = false, unique = true)
	@Enumerated(STRING)
	private Category category;

	@Column(nullable = false)
	private String description;

	@Column(nullable = false, columnDefinition = "text")
	private String content;

	public static About create(Category category, String description, String content) {
		return About.builder()
			.category(category)
			.description(description)
			.content(content)
			.build();
	}

	public void updateDescription(String description) {
		this.description = description;
	}

	public void updateContent(String content) {
		this.content = content;
	}
}
