package ru.keeapps.backup.model.security;

import javax.naming.AuthenticationException;

public interface AuthenticationService {
    void authenticate(String username, String password) throws AuthenticationException;
}
