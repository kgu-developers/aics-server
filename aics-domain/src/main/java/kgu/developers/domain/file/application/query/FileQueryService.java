package kgu.developers.domain.file.application.query;

import kgu.developers.domain.file.domain.FileEntity;
import kgu.developers.domain.file.domain.FileRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class FileQueryService {
	private final FileRepository fileRepository;

	public FileEntity getFileById(Long id) {
		return fileRepository.findById(id).orElse(null);
	}


	public Map<Long,FileEntity> findFileEntityMapByIds(List<Long> ids) {
		return fileRepository.findAllByIds(ids)
				.stream()
				.collect(Collectors.toMap(FileEntity::getId, Function.identity()));
	}
}
