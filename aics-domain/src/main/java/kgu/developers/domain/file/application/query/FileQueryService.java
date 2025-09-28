package kgu.developers.domain.file.application.query;

import kgu.developers.domain.file.domain.FileModel;
import kgu.developers.domain.file.domain.FileRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class FileQueryService {
	private final FileRepository fileRepository;

	public FileModel getFileById(Long id) {
		return fileRepository.findById(id).orElse(null);
	}

	public Map<Long,String> findPhysicalPathMapByIds(List<Long> ids) {
		return fileRepository.findAllByIds(ids)
				.stream()
				.collect(Collectors.toMap(FileModel::getId, FileModel::getPhysicalPath));
	}

	public String getFilePhysicalPath(Long id) {
		return fileRepository.findPhysicalPathById(id)
			.orElse(null);
	}
}
