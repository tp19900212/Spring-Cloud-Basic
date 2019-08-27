package com.quyc.apione.validate;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Objects;

/**
 * @progrem: learn
 * @description: Validated Exception Handler
 * @author: quyc
 * @create: 2019-08-27 23:36:37
 */
@RestControllerAdvice
public class MethodArgumentNotValidExceptionHandler {

    /**
     * 校验异常的全局捕获并组装返回信息
     *
     * @param e
     * @return
     */
    @ExceptionHandler(BindException.class)
    public ResponseEntity<String> methodArgumentNotValidException(BindException e) {
        FieldError fieldError = e.getFieldError();
        String defaultMessage = Objects.requireNonNull(fieldError).getDefaultMessage();
        return new ResponseEntity<>(defaultMessage, HttpStatus.BAD_REQUEST);
    }

}
