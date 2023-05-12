package com.br.igorsily.webfluxcourse.service.impl;

import com.br.igorsily.webfluxcourse.entity.User;
import com.br.igorsily.webfluxcourse.exception.ResourceNotFoundException;
import com.br.igorsily.webfluxcourse.mapper.UserMapper;
import com.br.igorsily.webfluxcourse.model.request.UserRequest;
import com.br.igorsily.webfluxcourse.repository.UserRepository;
import com.github.javafaker.Faker;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import java.util.Locale;

import static java.lang.String.format;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

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
    @DisplayName("Should save a user")
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

        when(userMapper.toEntity(any(UserRequest.class))).thenReturn(enitty);

        when(userRepository.save(any(User.class))).thenReturn(Mono.just(enitty));

        Mono<User> result = userService.save(userRequest);

        StepVerifier.create(result)
                .expectNextMatches(user -> user.getClass().equals(User.class))
                .expectComplete()
                .verify();

    }

    @Test
    @DisplayName("Should find a user by id")
    void findById() {

        User entity = User.builder()
                .name(faker.name().fullName())
                .email(faker.internet().emailAddress())
                .password(faker.internet().password())
                .build();

        when(userRepository.findById(anyString())).thenReturn(Mono.just(entity));

        Mono<User> result = userService.findById(anyString());

        StepVerifier.create(result)
                .expectNextMatches(user -> user.getClass().equals(User.class))
                .expectComplete()
                .verify();

    }

    @Test
    @DisplayName("Should be return ResourceNotFoundException")
    void findByIdReturnResourceNotFoundException() {


        String messageErro = format("User not found with id %s", faker.internet().uuid());

        when(userRepository.findById(anyString())).thenReturn(Mono.error(new ResourceNotFoundException(
                messageErro
        )));

        Mono<?> result = userService.findById(anyString());

        StepVerifier.create(result)
                .expectErrorMatches(throwable -> throwable instanceof ResourceNotFoundException &&
                        throwable.getMessage().equals(messageErro))
                .verify();

    }

    @Test
    @DisplayName("Should be return all users")
    void findAll() {

        when(userRepository.findAll()).thenReturn(Flux.just(User.builder().build()));

        Flux<User> result = userService.findAll();

        StepVerifier.create(result)
                .expectNextMatches(user -> user.getClass().equals(User.class))
                .expectComplete()
                .verify();

        verify(userRepository, times(1)).findAll();
    }

    @Test
    @DisplayName("Should be update a user")
    void update() {
        UserRequest userRequest = new UserRequest(
                faker.name().fullName(),
                faker.internet().emailAddress(),
                faker.internet().password()
        );

        User entity = User.builder()
                .name(userRequest.name())
                .email(userRequest.email())
                .password(userRequest.password())
                .build();

        when(userMapper.toEntity(any(UserRequest.class), any(User.class))).thenReturn(entity);
        when(userRepository.findById(anyString())).thenReturn(Mono.just(entity));
        when(userRepository.save(any(User.class))).thenReturn(Mono.just(entity));

        Mono<User> result = userService.update(anyString(), userRequest);

        StepVerifier.create(result)
                .expectNextMatches(user -> user.getClass().equals(User.class))
                .expectComplete()
                .verify();

        verify(userRepository, times(1)).save(any(User.class));

    }


}