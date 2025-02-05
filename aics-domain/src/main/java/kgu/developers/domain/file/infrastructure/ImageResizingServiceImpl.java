package kgu.developers.domain.file.infrastructure;

import java.io.File;
import java.io.IOException;

import org.springframework.stereotype.Service;

import net.coobird.thumbnailator.Thumbnails;

@Service
public class ImageResizingServiceImpl implements ImageResizingService {
	public File imageResize(File inputFile, int width, int height, double quality) throws IOException {
		File outputFile = new File(inputFile.getParent(), inputFile.getName());

		Thumbnails.of(inputFile)
			.size(width, height)
			.outputQuality(quality)
			.keepAspectRatio(true)
			.toFile(outputFile);

		return outputFile;
	}
}
