package com.viniciusfinger.post.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.viniciusfinger.post.domain.User;

public interface UserRepository extends MongoRepository<User, String>{

}
