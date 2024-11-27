package mock;

import kgu.developers.common.domain.BaseTimeEntity;

import java.lang.reflect.Field;
import java.time.LocalDateTime;

public class TestEntityUtils {
	public static void setCreatedAt(BaseTimeEntity entity, LocalDateTime createdAt) {
		try {
			Field field = BaseTimeEntity.class.getDeclaredField("createdAt");
			field.setAccessible(true);
			field.set(entity, createdAt);
		} catch (NoSuchFieldException | IllegalAccessException e) {
			throw new RuntimeException("TestEntityUtils에서 createdAt 설정 실패", e);
		}
	}
}

