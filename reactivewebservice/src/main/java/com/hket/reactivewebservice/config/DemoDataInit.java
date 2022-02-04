package com.hket.reactivewebservice.config;

import java.util.UUID;

import com.hket.reactivewebservice.entity.User;
import com.hket.reactivewebservice.repository.UserRepository;

import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import reactor.core.publisher.Flux;

@Log4j2
@Component
@AllArgsConstructor
@org.springframework.context.annotation.Profile("local")
public class DemoDataInit implements ApplicationListener<ApplicationReadyEvent>{
    
    private final UserRepository userRepository;

    @Override
    public void onApplicationEvent(ApplicationReadyEvent event) {
        userRepository.deleteAll()
            .thenMany(
                Flux.just("John", "David","Jim")
                .map(name -> new User(UUID.randomUUID().toString(), name, name.toLowerCase()+"@email.com"))
                .flatMap(userRepository::save)
            )
            .thenMany(userRepository.findAll())
            .subscribe(user -> log.info("Saving user " + user.getName() + " : " + user.getId()));
    }
}
