package kgu.developers.domain.file.infrastructure;

import java.io.File;
import java.io.IOException;

public interface ImageResizingService {
	File imageResize(File inputFile, int width, int height, double quality) throws IOException;
}
