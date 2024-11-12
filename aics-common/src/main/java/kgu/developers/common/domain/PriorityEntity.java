package kgu.developers.common.domain;

public interface PriorityEntity {
	Integer getPriority();

	void updatePriority(Integer priority);

	boolean isPriorityEqual(Integer priority);
}
