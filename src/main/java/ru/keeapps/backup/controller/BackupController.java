package ru.keeapps.backup.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.keeapps.backup.model.security.AuthenticationService;
import ru.keeapps.backup.model.service.BackupService;
import ru.keeapps.backup.controller.dto.ResponseMessage;

import javax.naming.AuthenticationException;

@RestController
@RequestMapping("/api/v1/archive")
@RequiredArgsConstructor
public class BackupController {
    private final BackupService backupService;
    private final AuthenticationService authenticationService;

    @GetMapping
    public ResponseEntity<?> doArchive(@RequestParam("user") String username, @RequestParam("pass") String password) {
        try {
            authenticationService.authenticate(username, password);
        } catch (AuthenticationException e) {
            return ResponseMessage.create("Authentication failed", HttpStatus.FORBIDDEN);
        }

        try {
            backupService.backup();
        } catch (Exception e) {
            return ResponseMessage.create(message(e), HttpStatus.NOT_FOUND);
        }
        return ResponseMessage.create("Backup completed", HttpStatus.OK);
    }

    private String message(Exception e) {
        return String.format("Backup failed: %s", e.getMessage());
    }
}
