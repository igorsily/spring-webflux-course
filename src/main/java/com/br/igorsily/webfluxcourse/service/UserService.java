package com.br.igorsily.webfluxcourse.service;

import com.br.igorsily.webfluxcourse.entity.User;
import com.br.igorsily.webfluxcourse.model.request.UserRequest;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface UserService {

    Mono<User> save(final UserRequest request);

    Mono<User> findById(final String id);

    Flux<User> findAll();

    Mono<User> update(final String id, final UserRequest request);

    Mono<Void> delete(final String id);
}
