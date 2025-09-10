package kgu.developers.domain.about.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor
public class About{

	private Long id;
	private Category category;
	private String content;

	public static About create(Category category, String content) {
		return About.builder()
			.category(category)
			.content(content)
			.build();
	}

	public void updateContent(String content) {
		this.content = content;
	}
}
