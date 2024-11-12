package kgu.developers.api.priority.application;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import jakarta.persistence.EntityManager;
import kgu.developers.common.domain.PriorityEntity;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class PriorityService<T extends PriorityEntity> {
	private final EntityManager entityManager;

	@Transactional(readOnly = true)
	public int adjustToMaxPlusOne(Class<T> entityType, Integer priority) {
		int maxPriority = findMaxPriority(entityType);
		return (priority > maxPriority) ? maxPriority + 1 : priority;
	}

	@Transactional
	public void updatePriority(Class<T> entityType, int effectivePriority) {
		String query = "UPDATE " + entityType.getSimpleName() + " e " +
			"SET e.priority = e.priority + 1 " +
			"WHERE e.priority >= :effectivePriority";
		entityManager.createQuery(query)
			.setParameter("effectivePriority", effectivePriority)
			.executeUpdate();
	}
	
	private int findMaxPriority(Class<T> entityType) {
		return entityManager.createQuery(
				"SELECT COALESCE(MAX(e.priority), 0) FROM " + entityType.getSimpleName() + " e", Integer.class)
			.getSingleResult();
	}
}
