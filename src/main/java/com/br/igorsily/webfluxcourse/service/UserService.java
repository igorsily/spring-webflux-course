package com.br.igorsily.webfluxcourse.service;

import com.br.igorsily.webfluxcourse.entity.User;
import com.br.igorsily.webfluxcourse.model.request.UserRequest;
import reactor.core.publisher.Mono;

public interface UserService {

    Mono<User> save(final UserRequest request);
}