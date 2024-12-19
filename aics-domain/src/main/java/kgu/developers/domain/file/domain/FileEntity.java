package kgu.developers.domain.file.domain;

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
public class FileEntity extends BaseTimeEntity {
	@Id
	@GeneratedValue(strategy = IDENTITY)
	private Long id;

	@Column(nullable = false)
	private String logicalName;

	@Column(nullable = false, unique = true)
	private String physicalPath;

	@Column(nullable = false)
	private Long fileSize;

	@Column(nullable = false)
	private String extension;

	public static FileEntity create(String logicalName, String physicalPath, Long fileSize, String extension) {
		return FileEntity.builder()
			.logicalName(logicalName)
			.physicalPath(physicalPath)
			.fileSize(fileSize)
			.extension(extension)
			.build();
	}
}
