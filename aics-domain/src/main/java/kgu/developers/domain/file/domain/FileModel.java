package kgu.developers.domain.file.domain;

import java.time.LocalDateTime;

import kgu.developers.globalutils.encryption.AesUtil;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class FileModel {
	private final Long id;
	private String logicalName;
	private String physicalPath;
	private String fileSize;
	private String extension;
	private LocalDateTime createdAt;
	private LocalDateTime updatedAt;
	private LocalDateTime deletedAt;

	private static final long KB = 1024L;
	private static final long MB = KB * 1024;
	private static final long GB = MB * 1024;

	public static FileModel create(String logicalName, String physicalPath, long sizeInBytes, String extension) {
		String readableFileSize = convertToReadableFileSize(sizeInBytes);
		String encryptedLogicalName = AesUtil.encrypt(logicalName);
		String encryptedPhysicalPath = AesUtil.encrypt(physicalPath);
		return new FileModel(
			null,
			encryptedLogicalName,
			encryptedPhysicalPath,
			readableFileSize,
			extension,
			LocalDateTime.now(),
			LocalDateTime.now(),
			null
		);
	}

	private static String convertToReadableFileSize(long sizeInBytes) {
		if (sizeInBytes < KB) return sizeInBytes + " Bytes";
		else if (sizeInBytes < MB) return String.format("%.2f KB", sizeInBytes / (double) KB);
		else if (sizeInBytes < GB) return String.format("%.2f MB", sizeInBytes / (double) MB);
		else return String.format("%.2f GB", sizeInBytes / (double) GB);
	}
}
