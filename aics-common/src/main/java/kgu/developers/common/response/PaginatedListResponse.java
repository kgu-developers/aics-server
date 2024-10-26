package kgu.developers.common.response;

import java.util.List;

import lombok.Builder;

@Builder
public record PaginatedListResponse<T>(
	List<T> contents,
	PageableResponse pageable
) {
	public static <T> PaginatedListResponse<T> of(List<T> contents, PageableResponse pageable) {
		return PaginatedListResponse.<T>builder()
			.contents(contents)
			.pageable(pageable)
			.build();
	}
}
