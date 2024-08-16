package com.github.aliftrd.sei.utils.response;

import java.util.LinkedHashMap;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import lombok.Setter;
import lombok.val;

@Setter
@Component
public class ResponseFormatter {
    
    protected HttpStatus status = HttpStatus.OK;
    protected String message;
    protected Object data;
    protected Object errors;


    public ResponseEntity<Object> success(String message) {
        setMessage(message);

        return buildResponse();
    }

    public ResponseEntity<Object> buildResponse() {
        val response = ResponseEntity.status(status).contentType(MediaType.APPLICATION_JSON).body(buildBody());

        return response;
    }

    private Object buildBody() {
        LinkedHashMap<String, Object> body = new LinkedHashMap<>();
        body.put("message", message);
        body.put("data", data);
        return body;
    } 
}
