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

	@Transactional
	public int createAdjustPriority(Class<T> entityType, Integer priority) {
		int adjustedPriority = adjustPriority(entityType, priority) + 1;
		createPriority(entityType, adjustedPriority);
		return adjustedPriority;
	}

	@Transactional
	public int updateAdjustPriority(Class<T> entityType, Integer priority, Integer updatePriority) {
		int adjustedPriority = adjustPriority(entityType, updatePriority);
		updatePriority(entityType, priority, adjustedPriority);
		return adjustedPriority;
	}

	private int adjustPriority(Class<T> entityType, Integer priority) {
		int maxPriority = findMaxPriority(entityType);
		return (priority > maxPriority) ? maxPriority : priority;
	}

	private void createPriority(Class<T> entityType, int effectivePriority) {
		String query = "UPDATE " + entityType.getSimpleName() + " e " +
			"SET e.priority = e.priority + 1 " +
			"WHERE e.priority >= :effectivePriority";
		entityManager.createQuery(query)
			.setParameter("effectivePriority", effectivePriority)
			.executeUpdate();
	}

	private void updatePriority(Class<T> entityType, Integer currentPriority, Integer newPriority) {
		String query;

		if (currentPriority < newPriority) {
			query = "UPDATE " + entityType.getSimpleName() + " e " +
				"SET e.priority = e.priority - 1 " +
				"WHERE e.priority > :currentPriority AND e.priority <= :newPriority";
		} else {
			query = "UPDATE " + entityType.getSimpleName() + " e " +
				"SET e.priority = e.priority + 1 " +
				"WHERE e.priority >= :newPriority AND e.priority < :currentPriority";
		}

		entityManager.createQuery(query)
			.setParameter("currentPriority", currentPriority)
			.setParameter("newPriority", newPriority)
			.executeUpdate();
	}

	private int findMaxPriority(Class<T> entityType) {
		return entityManager.createQuery(
				"SELECT COALESCE(MAX(e.priority), 0) FROM " + entityType.getSimpleName() + " e", Integer.class)
			.getSingleResult();
	}

}
