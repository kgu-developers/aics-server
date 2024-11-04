package kgu.developers.domain.lab.domain;

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
public class Lab extends BaseTimeEntity {
	@Id
	@GeneratedValue(strategy = IDENTITY)
	private Long id;

	@Column(nullable = false, length = 16)
	private String name;

	@Column(nullable = false, length = 10)
	private String loc;

	@Column(nullable = false, length = 50)
	private String site;

	public static Lab create(String name, String loc, String site) {
		return Lab.builder()
			.name(name)
			.loc(loc)
			.site(site)
			.build();
	}

	public void updateName(String name) {
		this.name = name;
	}

	public void updateLoc(String loc) {
		this.loc = loc;
	}

	public void updateSite(String site) {
		this.site = site;
	}
}
