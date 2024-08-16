package com.github.aliftrd.sei.utils.exception;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.NoHandlerFoundException;

import com.github.aliftrd.sei.utils.response.ResponseFormatter;

@Order(Ordered.HIGHEST_PRECEDENCE)
@RestControllerAdvice
public class ExceptionHandler {
    @Autowired
    private ResponseFormatter response;

     /**
     * Handle NoHandlerFoundException
     */
    @org.springframework.web.bind.annotation.ExceptionHandler(NoHandlerFoundException.class)
    public ResponseEntity<?> handleNoHandlerFoundException(Exception ex) {
        return response.error(HttpStatus.NOT_FOUND, ex.getMessage());
    }

    /**
     * Handle HttpMessageNotReadableException
     */
    @org.springframework.web.bind.annotation.ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<?> handleHttpMessageNotReadableException(Exception ex) {
        return response.error(HttpStatus.BAD_REQUEST, ex.getMessage());
    }

    /**
     * Handle BindException
     */
    @org.springframework.web.bind.annotation.ExceptionHandler(BindException.class)
    public ResponseEntity<?> handleBindException(BindException ex) {
        BindingResult result = ex.getBindingResult();
        List<FieldError> fieldErrors = result.getFieldErrors();

        // return response.error(HttpStatus.BAD_REQUEST, "error.validation",
        // result.getAllErrors());

        return response.error(HttpStatus.BAD_REQUEST, "error validation",
                handleFieldError(fieldErrors));
    }

    /**
     * Handle Exception
     */
    @org.springframework.web.bind.annotation.ExceptionHandler(Exception.class)
    public ResponseEntity<?> handleAllError(Exception ex) {
        HttpStatus status = HttpStatus.INTERNAL_SERVER_ERROR;
        String msg = ex.getMessage();
        Object errors = null;

        if (msg == null) {
            msg = "no message error from exception";
        }

        // if (msg.indexOf(":::") > -1) {
        // String[] tmp = msg.split(":::");
        // status = HttpStatus.resolve(Integer.valueOf(tmp[0]));
        // msg = tmp[tmp.length - 1];

        // ApiException me = (ApiException) ex;
        // List<FieldError> fieldError = me.getFieldErrors();
        // if (fieldError.size() > 0) {
        // errors = handleFieldError(fieldError);
        // }
        // } else if (ex.getCause() != null) {
        Throwable throwable = ex;
        while (throwable.getCause() != null) {
            throwable = throwable.getCause();
        }

        msg = throwable.getLocalizedMessage();
        // }

        return response.error(status, msg, errors);
    }

    /**
    *
    */
    private HashMap<String, Object> handleFieldError(List<FieldError> fieldErrors) {
        LinkedHashMap<String, Object> lfe = new LinkedHashMap<String, Object>();

        for (FieldError fieldError : fieldErrors) {
            String field = fieldError.getField();
            String msg = fieldError.getDefaultMessage();

            HashMap<String, String> typeError = new HashMap<String, String>();
            typeError.put("NotBlank", "%s is required");
            typeError.put("NotEmpty", "%s is required");
            typeError.put("Email", "%s is not a valid email");

            if (typeError.containsKey(fieldError.getCode())) {
                msg = typeError.get(fieldError.getCode());
            }

            // lfe.put(field, String.format(response.getMessageLanguage(msg), field));
            lfe.put(field, String.format(msg, field));

        }

        return lfe;
    }
}