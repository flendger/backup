package ru.keeapps.backup.model.security;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.keeapps.backup.model.security.config.AccessConfiguration;

import javax.naming.AuthenticationException;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class AuthenticationServiceImpl implements AuthenticationService {
    private final AccessConfiguration accessConfiguration;

    @Override
    public void authenticate(String username, String password) throws AuthenticationException {
        if (Objects.isNull(username) || Objects.isNull(password)) {
            throw createAuthException();
        }

        if (!Objects.equals(username, accessConfiguration.getUsername()) && !Objects.equals(password, accessConfiguration.getPassword())) {
            throw createAuthException();
        }
    }

    private AuthenticationException createAuthException() {
        return new AuthenticationException("Authentication failed");
    }
}
