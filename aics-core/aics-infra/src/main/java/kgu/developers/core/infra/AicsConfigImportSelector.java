package kgu.developers.core.infra;

import java.util.Arrays;
import java.util.Map;

import org.springframework.context.annotation.DeferredImportSelector;
import org.springframework.core.type.AnnotationMetadata;
import org.apache.commons.collections4.MapUtils;

class AicsConfigImportSelector implements DeferredImportSelector {
	@Override
	public String[] selectImports(AnnotationMetadata metadata) {
		return Arrays.stream(getValues(metadata))
			.map(v -> v.getConfigClass().getName())
			.toArray(String[]::new);
	}

	private AicsConfigGroup[] getValues(AnnotationMetadata metadata) {
		Map<String, Object> attributes = metadata.getAnnotationAttributes(EnableAicsConfig.class.getName());
		return (AicsConfigGroup[])MapUtils.getObject(attributes, "value", new AicsConfigGroup[] {});
	}
}
