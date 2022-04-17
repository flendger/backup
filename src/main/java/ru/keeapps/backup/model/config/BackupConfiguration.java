package ru.keeapps.backup.model.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Getter
@Setter
@Component
public class BackupConfiguration {
    @Value("${backup.source}")
    private String source;

    @Value("${backup.destination}")
    private String destination;
}
