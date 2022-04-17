package ru.keeppas.backup.controller.dto;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@AllArgsConstructor
public class ResponseMessage {
    private String message;
    private int status;

    public static ResponseEntity<ResponseMessage> create(String message, HttpStatus status) {
        return new ResponseEntity<>(new ResponseMessage(message, status.value()), status);
    }
}
