package com.hket.reactivewebservice.service;

import com.hket.reactivewebservice.entity.User;
import com.hket.reactivewebservice.repository.UserRepository;

import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;


@Service
@AllArgsConstructor
public class UserService {
    
    private final UserRepository userRepository;

    public Flux<User> findAll() {
        return userRepository.findAll();
    }

    public Mono<User> findById(String id) {
        return userRepository.findById(id);
    }

    public Mono<User> save(User user) {
        return userRepository.save(user);
    }

    public Mono<User> delete(String id) {
        return userRepository.findById(id)
            .flatMap(user -> userRepository.deleteById(user.getId()).thenReturn(user));
    }
}
