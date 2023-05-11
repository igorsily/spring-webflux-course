package com.br.igorsily.webfluxcourse.controller;

import com.br.igorsily.webfluxcourse.model.request.UserRequest;
import com.br.igorsily.webfluxcourse.model.response.UserResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface UserController {

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    ResponseEntity<Mono<Void>> save(@Validated(UserRequest.UserRequestView.UserRequestCreateView.class) @RequestBody UserRequest request);

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    ResponseEntity<Mono<UserResponse>> findById(@PathVariable("id") String id);

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    ResponseEntity<Flux<UserResponse>> findAll();

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    ResponseEntity<Mono<UserResponse>> update(@PathVariable("id") String id, @Validated(UserRequest.UserRequestView.UserRequestUpdateView.class) @RequestBody UserRequest request);

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    ResponseEntity<Mono<Void>> delete(@PathVariable("id") String id);
}
