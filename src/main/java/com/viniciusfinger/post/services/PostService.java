package com.viniciusfinger.post.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.viniciusfinger.post.domain.Post;
import com.viniciusfinger.post.repositories.PostRepository;
import com.viniciusfinger.post.services.exceptions.ObjectNotFoundException;

@Service
public class PostService {
	
	@Autowired
	public PostRepository repository;
	
	public Post findById(String id) {
		Optional<Post> optPost = repository.findById(id);
		if (optPost.isEmpty()) {
			throw new ObjectNotFoundException("Objeto não encontrado no banco.");
		} else {
			Post post = optPost.get();
			return post;
		}
	}
}
