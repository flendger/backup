package ru.keeapps.backup.controller.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@AllArgsConstructor
@Getter
@Setter
public class ResponseMessage {
    private String message;
    private int status;

    public static ResponseEntity<ResponseMessage> create(String message, HttpStatus status) {
        return new ResponseEntity<>(new ResponseMessage(message, status.value()), status);
    }
}
