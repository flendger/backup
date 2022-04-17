package ru.keeapps.backup.model.security.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Getter
@Setter
@Component
public class AccessConfiguration {
    @Value("${backup.username}")
    private String username;

    @Value("${backup.password}")
    private String password;
}
