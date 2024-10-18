package kgu.developers.core.domain.major;

import jakarta.persistence.*;
import kgu.developers.core.common.domain.BaseTimeEntity;
import kgu.developers.core.domain.user.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import static jakarta.persistence.FetchType.LAZY;
import static jakarta.persistence.GenerationType.IDENTITY;
import static lombok.AccessLevel.PROTECTED;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor(access = PROTECTED)
@AllArgsConstructor
public class Major extends BaseTimeEntity {
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "major_id")
	private Long id;

	@Column(nullable = false, length = 10)
	private String majorName;

	@OneToMany(fetch = LAZY, mappedBy = "majorId", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<User> affiliates = new ArrayList<>();

	public void addAffiliate(User affiliate) {
		affiliates.add(affiliate);
	}

	public boolean removeAffiliate(User removeUser) {
		Long removeUserId = removeUser.getId();

		return affiliates.removeIf(affiliate -> affiliate.getId().equals(removeUserId));
	}

	public boolean isAffiliate(User findUser) {
		Long findUserId = findUser.getId();

		return affiliates.stream().anyMatch(affiliate -> affiliate.getId().equals(findUserId));
	}

	public int getAffiliateCount() {
		return affiliates.size();
	}

	public void clearAffiliates() {
		affiliates.clear();
	}

}
