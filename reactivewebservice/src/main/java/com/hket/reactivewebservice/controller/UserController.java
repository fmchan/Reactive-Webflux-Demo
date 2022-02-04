package com.hket.reactivewebservice.controller;

import com.hket.reactivewebservice.entity.User;
import com.hket.reactivewebservice.service.UserService;

import org.reactivestreams.Publisher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/user", produces = MediaType.APPLICATION_JSON_VALUE)
public class UserController {
    
    @Autowired
    private UserService userservice;

    @GetMapping
    public Publisher<User> getAll() {
        return userservice.findAll();
    }

    @GetMapping("/{id}")
    public Publisher<User> getById(@PathVariable(value = "id") String id) {
        return userservice.findById(id);
    }

    @PostMapping
    public Publisher<User> createUser(@RequestBody User user) {
        return userservice.save(user);
    }

    @DeleteMapping("/{id}")
    public Publisher<User> deleteById(@PathVariable(value = "id") String id) {
        return userservice.delete(id);
    }

    @PutMapping
    public Publisher<User> updateUser(@RequestBody User user) {
        return userservice.save(user);
    }
}
