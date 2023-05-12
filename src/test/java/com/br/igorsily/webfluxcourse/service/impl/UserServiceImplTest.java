package com.br.igorsily.webfluxcourse.service.impl;

import com.br.igorsily.webfluxcourse.entity.User;
import com.br.igorsily.webfluxcourse.mapper.UserMapper;
import com.br.igorsily.webfluxcourse.model.request.UserRequest;
import com.br.igorsily.webfluxcourse.repository.UserRepository;
import com.github.javafaker.Faker;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import java.util.Locale;
import java.util.Objects;

import static org.mockito.ArgumentMatchers.any;

@ExtendWith(MockitoExtension.class) // This annotation is used to tell JUnit 5 to enable Mockito
class UserServiceImplTest {
    private final Faker faker = new Faker(new Locale("pt", "BR"));

    @Mock
    private UserRepository userRepository;

    @Mock
    private UserMapper userMapper;

    @InjectMocks
    private UserServiceImpl userService;

    @Test
    void save() {

        UserRequest userRequest = new UserRequest(
                faker.name().fullName(),
                faker.internet().emailAddress(),
                faker.internet().password()
        );

        User enitty = User.builder()
                .name(userRequest.name())
                .email(userRequest.email())
                .password(userRequest.password())
                .build();

        Mockito.when(userMapper.toEntity(any(UserRequest.class))).thenReturn(enitty);

        Mockito.when(userRepository.save(any(User.class))).thenReturn(Mono.just(enitty));

        Mono<User> result = userService.save(userRequest);

        StepVerifier.create(result)
                .expectNextMatches(Objects::nonNull)
                .expectComplete()
                .verify();

    }
}