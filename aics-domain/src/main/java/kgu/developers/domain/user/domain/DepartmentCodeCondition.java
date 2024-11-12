package kgu.developers.domain.user.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@Getter
@AllArgsConstructor
public enum DepartmentCodeCondition {
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

	public static DepartmentCodeCondition from(String code) {
		for (DepartmentCodeCondition condition : values()) {
			if (condition.year.equals(code)) {
				return condition;
			}
		}
		return null;
	}

	public static boolean isValidDepartmentCode(String id) {
		if (id == null || !id.matches("\\d{9}")) {
			return false;
		}

		String year = id.substring(2, 4);
		String code = id.substring(4, 6);

		DepartmentCodeCondition condition = DepartmentCodeCondition.from(year);
		return condition != null && condition.isValidCode(code);
	}

	public boolean isValidCode(String value) {
		return validCode.contains(value);
	}
}
