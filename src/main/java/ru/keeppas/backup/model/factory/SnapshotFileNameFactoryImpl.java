package ru.keeppas.backup.model.factory;

import org.apache.commons.io.FilenameUtils;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.attribute.FileTime;
import java.time.LocalDateTime;
import java.time.ZoneId;

@Service
public class SnapshotFileNameFactoryImpl implements SnapshotFileNameFactory {
    @Override
    public String make(Path source) throws Exception {

        LocalDateTime fileDate = getFileDate(source);

        String sourceFileName = source.getFileName().toString();

        return createSnapshotName(sourceFileName, fileDate);
    }

    private LocalDateTime getFileDate(Path source) throws IOException {
        FileTime lastModifiedTime = Files.getLastModifiedTime(source);
        return LocalDateTime.ofInstant(lastModifiedTime.toInstant(), ZoneId.systemDefault());
    }

    private String createSnapshotName(String sourceFileName, LocalDateTime fileDate) {
        StringBuilder nameBuilder = new StringBuilder();

        nameBuilder
                .append(FilenameUtils.getBaseName(sourceFileName))
                .append("_")
                .append(fileDate.toString());

        String extension = FilenameUtils.getExtension(sourceFileName);
        if (!extension.isEmpty()) {
            nameBuilder
                    .append(".")
                    .append(extension);
        }

        return nameBuilder.toString();
    }
}
