package com.viniciusfinger.post.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.viniciusfinger.post.domain.Post;

public interface PostRepository extends MongoRepository<Post, String>{

}
