package com.github.aliftrd.sei.utils.response;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.util.LinkedHashMap;

@Component
public class ResponseFormatter {

    public ResponseEntity<?> success(String message) {
        return buildResponse(HttpStatus.OK, message, null, null);
    }

    public ResponseEntity<?> success(String message, Object data) {
        return buildResponse(HttpStatus.OK, message, data, null);
    }

    public ResponseEntity<?> error(String message) {
        return buildResponse(HttpStatus.BAD_REQUEST, message, null, null);
    }

    public ResponseEntity<?> error(String message, Object data) {
        return buildResponse(HttpStatus.BAD_REQUEST, message, null, data);
    }

    public ResponseEntity<?> error(HttpStatus status, String message) {
        return buildResponse(status, message, null, null);
    }

    public ResponseEntity<?> error(HttpStatus status, String message, Object data) {
        return buildResponse(status, message, null, data);
    }

    private ResponseEntity<Object> buildResponse(HttpStatus status, String message, Object data, Object errors) {
        LinkedHashMap<String, Object> body = new LinkedHashMap<>();
        body.put("message", message);
        
        if(data != null) {
            body.put("data", data);
        }

        if (errors != null) {
            body.put("errors", errors);
        }

        return ResponseEntity.status(status).contentType(MediaType.APPLICATION_JSON).body(body);
    }
}
