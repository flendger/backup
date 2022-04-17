package ru.keeppas.backup.model.factory;

import java.nio.file.Path;

public interface SnapshotFileNameFactory {
    String make(Path source) throws Exception;
}
