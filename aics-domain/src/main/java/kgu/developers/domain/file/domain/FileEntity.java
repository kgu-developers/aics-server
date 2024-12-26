package kgu.developers.domain.file.domain;

import static jakarta.persistence.GenerationType.IDENTITY;
import static lombok.AccessLevel.PROTECTED;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import kgu.developers.common.domain.BaseTimeEntity;
import kgu.developers.globalutils.encryption.AesUtil;
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
	private String fileSize;

	@Column(nullable = false)
	private String extension;

	private static final long KB = 1024L;
	private static final long MB = KB * 1024;
	private static final long GB = MB * 1024;

	public static FileEntity create(String logicalName, String physicalPath, Long fileSize, String extension) {
		String readableFileSize = convertToReadableFileSize(fileSize);
		String encryptedLogicalName = AesUtil.encrypt(logicalName);
		String encryptedPhysicalPath = AesUtil.encrypt(physicalPath);
		return FileEntity.builder()
			.logicalName(encryptedLogicalName)
			.physicalPath(encryptedPhysicalPath)
			.fileSize(readableFileSize)
			.extension(extension)
			.build();
	}

	private static String convertToReadableFileSize(long sizeInBytes) {
		if (sizeInBytes < KB) return sizeInBytes + " Bytes";
		else if (sizeInBytes < MB) return String.format("%.2f KB", sizeInBytes / (double) KB);
		else if (sizeInBytes < GB) return String.format("%.2f MB", sizeInBytes / (double) MB);
		else return String.format("%.2f GB", sizeInBytes / (double) GB);
	}
}
