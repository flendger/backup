package ru.keeppas.backup.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.keeppas.backup.controller.dto.ResponseMessage;
import ru.keeppas.backup.model.service.BackupService;

@RestController
@RequestMapping("/api/v1/archive")
@RequiredArgsConstructor
public class BackupController {
    private final BackupService backupService;

    @PostMapping
    public ResponseEntity<?> doArchive() {
        try {
            backupService.backup();
            return ResponseMessage.create("Backup completed", HttpStatus.OK);
        } catch (Exception e) {
            return ResponseMessage.create(message(e), HttpStatus.BAD_GATEWAY);
        }
    }

    private String message(Exception e) {
        return String.format("Backup failed: %s", e.getMessage());
    }
}
