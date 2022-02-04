package com.hket.reactivewebservice.repository;
import com.hket.reactivewebservice.entity.User;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

public interface UserRepository extends ReactiveMongoRepository<User, String>{
    
}
