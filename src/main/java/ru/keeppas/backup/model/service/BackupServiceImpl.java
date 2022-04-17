package ru.keeppas.backup.model.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.keeppas.backup.model.BackupConfiguration;
import ru.keeppas.backup.model.factory.SnapshotFileNameFactory;

import java.io.IOException;
import java.nio.file.*;

@Service
@RequiredArgsConstructor
@Slf4j
public class BackupServiceImpl implements BackupService {
    private final BackupConfiguration backupConfiguration;
    private final SnapshotFileNameFactory fileNameFactory;

    @Override
    public void backup() throws Exception {
        Path source = Paths.get(backupConfiguration.getSource());
        checkSource(source);


        Path destination = Paths.get(backupConfiguration.getDestination());
        checkOrCreateDestination(destination);

        try {
            String newFileName = fileNameFactory.make(source);
            Path destinationFile = Paths.get(destination.toAbsolutePath().toString(), newFileName);

            Files.copy(source, destinationFile, StandardCopyOption.REPLACE_EXISTING);
        } catch (IOException e) {
            logAndThrowException(e);
        }
    }

    private void checkOrCreateDestination(Path destination) throws Exception {
        if (!Files.exists(destination)) {
            try {
                createDestinationDirectory(destination);
            } catch (IOException e) {
                logAndThrowException(e);
            }
        }

        if (!Files.isDirectory(destination)) {
            logAndThrowException(
                    new IOException(String.format("Destination is not directory: %s", backupConfiguration.getDestination())));
        }
    }

    private void checkSource(Path source) throws Exception {
        if (!Files.exists(source) || Files.isDirectory(source)) {
            logAndThrowException(
                    new IOException(String.format("Source file doesn't exist: %s", backupConfiguration.getSource())));
        }
    }

    private void logAndThrowException(Exception exception) throws Exception {
        log.error(exception.getMessage(), exception);
        throw exception;
    }

    private void createDestinationDirectory(Path destination) throws IOException {
        Files.createDirectories(destination);
    }
}
