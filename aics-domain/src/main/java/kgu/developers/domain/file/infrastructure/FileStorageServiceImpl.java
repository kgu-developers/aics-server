package kgu.developers.domain.file.infrastructure;

import static java.nio.file.StandardCopyOption.*;

import java.io.File;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Objects;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import kgu.developers.domain.file.domain.FileDomain;
import kgu.developers.domain.file.exception.FileDirectoryCreationFailedException;
import kgu.developers.domain.file.exception.FileNotFoundException;
import kgu.developers.domain.file.exception.FilePathInvalidException;
import kgu.developers.domain.file.exception.FileStoreFailedException;
import kgu.developers.domain.file.exception.NotSupportedFileExtensionException;

@Service
public class FileStorageServiceImpl implements FileStorageService {
    private final Path rootLocation;
    private final String url;
    private final Set<String> disallowedExtensions;

    @Autowired
    public FileStorageServiceImpl(FilePathProperties filePathProperties) {
        this.rootLocation = Paths.get(filePathProperties.getUploadPath())
                .toAbsolutePath().normalize();
        this.url = filePathProperties.getUrl();
        this.disallowedExtensions = filePathProperties.getDisallowedExtensions();
        try {
            Files.createDirectories(this.rootLocation);
        } catch (Exception e) {
            throw new FileDirectoryCreationFailedException();
        }
    }

    @Override
    public String store(MultipartFile file, FileDomain fileDomain, Long directoryId) {
        String fileName = StringUtils.cleanPath(Objects.requireNonNull(file.getOriginalFilename()));
        String path = getFullPath(fileDomain, directoryId, fileName);
        validateAttributes(path, fileName);
        try {
            Path targetLocation = this.rootLocation.resolve(path);
            Files.copy(file.getInputStream(), targetLocation, REPLACE_EXISTING);
            String relativePath = this.rootLocation.relativize(targetLocation).toString();
            return url + "/" + relativePath.replace(File.separator, "/");
        } catch (Exception e) {
            throw new FileStoreFailedException();
        }
    }

    @Override
    public Resource loadAsResource(String fileName) {
        try {
            Path filePath = rootLocation.resolve(fileName).normalize();
            Resource resource = new UrlResource(filePath.toUri());
            if (!resource.exists()) throw new FileNotFoundException();
            return resource;
        } catch (MalformedURLException | FileNotFoundException e) {
            throw new FileNotFoundException();
        }
    }

    @Override
    public void deleteAll() {

    }

    private void validateAttributes(String path, String fileName) {
        validateInvalidPath(path);
        validateExtension(fileName, disallowedExtensions);
    }

    private void validateExtension(String originalFileName, Set<String> disallowedExtensions) {
        String extension = originalFileName.substring(originalFileName.lastIndexOf('.') + 1).toLowerCase();
        if (disallowedExtensions.contains(extension)) throw new NotSupportedFileExtensionException();
    }

    private String getFullPath(FileDomain fileDomain, Long directoryId, String fileName) {
        try {
            Path domainPath = this.rootLocation.resolve(fileDomain.name().toLowerCase());
            if (!Files.exists(domainPath)) Files.createDirectories(domainPath);

            Path directoryPath = domainPath.resolve(String.valueOf(directoryId));
            if (!Files.exists(directoryPath)) Files.createDirectories(directoryPath);

            return directoryPath.resolve(fileName).toString();
        } catch (Exception e) {
            throw new FileDirectoryCreationFailedException();
        }
	}

    private static void validateInvalidPath(String fileName) throws FilePathInvalidException {
        if(fileName.contains("..")) throw new FilePathInvalidException();
    }
}
