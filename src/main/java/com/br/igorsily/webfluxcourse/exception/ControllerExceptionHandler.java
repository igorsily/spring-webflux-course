package com.br.igorsily.webfluxcourse.exception;

import com.br.igorsily.webfluxcourse.util.ExceptionUtil;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.support.WebExchangeBindException;
import reactor.core.publisher.Mono;

import java.util.ArrayList;
import java.util.List;

@ControllerAdvice
public class ControllerExceptionHandler {

    @ExceptionHandler(Exception.class)
    public final ResponseEntity<Mono<ExceptionResponse>> handleAllException(Exception e, ServerHttpRequest request) {
        ExceptionResponse exceptionResponse = ExceptionResponse.builder()
                .message(e.getMessage())
                .method(request.getMethod().toString())
                .path(request.getPath().toString())
                .build();

        return new ResponseEntity<>(Mono.just(exceptionResponse), HttpStatus.INTERNAL_SERVER_ERROR);

    }

    @ExceptionHandler(DuplicateKeyException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public final ResponseEntity<Mono<ExceptionResponse>> handleDuplicateKeyException(DuplicateKeyException e, ServerHttpRequest request) {
        ExceptionResponse exceptionResponse = ExceptionResponse.builder()
                .message(ExceptionUtil.getErrorDuplicateMessage(e.getMessage()))
                .method(request.getMethod().toString())
                .path(request.getPath().toString())
                .build();

        return new ResponseEntity<>(Mono.just(exceptionResponse), HttpStatus.BAD_REQUEST);

    }

    @ExceptionHandler(WebExchangeBindException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public final ResponseEntity<Mono<ExceptionResponse>> handleWebExchangeBindException(WebExchangeBindException e, ServerHttpRequest request) {

        List<ErrorValidation> errorValidationList = new ArrayList<>();
        e.getBindingResult().getFieldErrors().forEach(error -> errorValidationList.add(ErrorValidation.builder()
                .field(error.getField())
                .message(error.getDefaultMessage())
                .build()));

        ExceptionResponse exceptionResponse = ExceptionResponse.builder()
                .message("Validation error")
                .method(request.getMethod().toString())
                .path(request.getPath().toString())
                .errorValidationList(errorValidationList)
                .build();

        return new ResponseEntity<>(Mono.just(exceptionResponse), HttpStatus.BAD_REQUEST);
    }


}
