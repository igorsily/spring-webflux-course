package com.br.igorsily.webfluxcourse.controller.impl;


import com.br.igorsily.webfluxcourse.controller.UserController;
import com.br.igorsily.webfluxcourse.mapper.UserMapper;
import com.br.igorsily.webfluxcourse.model.request.UserRequest;
import com.br.igorsily.webfluxcourse.model.response.UserResponse;
import com.br.igorsily.webfluxcourse.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/v1/users")
@RequiredArgsConstructor
public class UserControllerImpl implements UserController {

    private final UserService userService;

    private final UserMapper userMapper;

    @Override
    public ResponseEntity<Mono<Void>> save(UserRequest request) {

        var user = userService.save(request).then();

        return new ResponseEntity<>(user, HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<Mono<UserResponse>> findById(String id) {

        var user = userService.findById(id).map(userMapper::toResponse);

        return new ResponseEntity<>(user, HttpStatus.OK);

    }

    @Override
    public ResponseEntity<Flux<UserResponse>> findAll() {


        var users = userService.findAll().map(userMapper::toResponse);

        return new ResponseEntity<>(users, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Mono<UserResponse>> update(String id, UserRequest request) {

        var user = userService.update(id, request).map(userMapper::toResponse);

        return new ResponseEntity<>(user, HttpStatus.OK);

    }

    @Override
    public ResponseEntity<Mono<Void>> delete(String id) {

        return new ResponseEntity<>(this.userService.delete(id)
                .then(), HttpStatus.OK);

    }
}
