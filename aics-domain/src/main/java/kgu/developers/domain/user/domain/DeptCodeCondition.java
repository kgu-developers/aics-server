package kgu.developers.domain.user.domain;

import java.util.List;

public enum DeptCodeCondition {
	CONDITION_18("18", List.of("10", "11", "12")),
	CONDITION_19("19", List.of("12")),
	CONDITION_20("20", List.of("14")),
	CONDITION_21("21", List.of("11")),
	CONDITION_22("22", List.of("11")),
	CONDITION_23("23", List.of("10", "11")),
	CONDITION_24("24", List.of("11", "12"))
	;

	private final String year;
	private final List<String> validCode;

	DeptCodeCondition(String year, List<String> validCode) {
		this.year = year;
		this.validCode = validCode;
	}

	public static DeptCodeCondition from(String code) {
		for (DeptCodeCondition condition : values()) {
			if (condition.year.equals(code)) {
				return condition;
			}
		}
		return null;
	}

	public boolean isValidCode(String value) {
		return validCode.contains(value);
	}
}
