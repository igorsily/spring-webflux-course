package com.br.igorsily.webfluxcourse.service.impl;

import com.br.igorsily.webfluxcourse.entity.User;
import com.br.igorsily.webfluxcourse.exception.ResourceNotFoundException;
import com.br.igorsily.webfluxcourse.mapper.UserMapper;
import com.br.igorsily.webfluxcourse.model.request.UserRequest;
import com.br.igorsily.webfluxcourse.repository.UserRepository;
import com.br.igorsily.webfluxcourse.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import static java.lang.String.format;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    private final UserMapper userMapper;

    @Override
    public Mono<User> save(UserRequest request) {
        return userRepository.save(userMapper.toEntity(request));
    }

    @Override
    public Mono<User> findById(String id) {

        return userRepository.findById(id).switchIfEmpty(Mono.error(new ResourceNotFoundException(
                format("User not found with id %s", id)
        )));
    }

    @Override
    public Flux<User> findAll() {
        return userRepository.findAll();
    }


}
