package kgu.developers.domain.file.infrastructure.entity;

import static jakarta.persistence.GenerationType.IDENTITY;
import static lombok.AccessLevel.PROTECTED;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import kgu.developers.common.domain.BaseTimeEntity;
import kgu.developers.domain.file.domain.FileModel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@Builder
@Table(name = "file_entity")
@AllArgsConstructor
@NoArgsConstructor(access = PROTECTED)
public class FileJpaEntity extends BaseTimeEntity {
	@Id
	@GeneratedValue(strategy = IDENTITY)
	private Long id;

	@Column(nullable = false)
	private String logicalName;

	@Column(nullable = false, unique = true)
	private String physicalPath;

	@Column(nullable = false)
	private String fileSize;

	@Column(nullable = false)
	private String extension;

	public static FileJpaEntity toEntity(FileModel file) {
		return FileJpaEntity.builder()
			.id(file.getId())
			.logicalName(file.getLogicalName())
			.physicalPath(file.getPhysicalPath())
			.fileSize(file.getFileSize())
			.extension(file.getExtension())
			.build();
	}

	public FileModel toDomain() {
		return new FileModel(
			this.id,
			this.logicalName,
			this.physicalPath,
			this.fileSize,
			this.extension,
			this.getCreatedAt(),
			this.getUpdatedAt(),
			this.getDeletedAt()
		);
	}
}

