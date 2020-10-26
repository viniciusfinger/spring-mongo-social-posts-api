package com.viniciusfinger.post.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.viniciusfinger.post.domain.User;
import com.viniciusfinger.post.dto.UserDTO;
import com.viniciusfinger.post.repositories.UserRepository;
import com.viniciusfinger.post.services.exceptions.ObjectNotFoundException;

@Service
public class UserService {

	@Autowired
	UserRepository repository;
	
	public List<User> findAll() {
		return repository.findAll();
	}
	
	public User findById(String id) {
		Optional<User> obj = repository.findById(id);
		if (obj.isEmpty()) {
			throw new ObjectNotFoundException("Objeto não encontrado no banco.");
		} else {
			User user = obj.get();
			return user; 
		}	
	}
	
	public User insert(User obj) {
		return repository.insert(obj);
	}
	
	public void delete(String id) {
		findById(id);
		repository.deleteById(id);
	}
	
	public User fromDTO (UserDTO objDTO) {
		return new User(objDTO.getId(), objDTO.getName(), objDTO.getEmail());
	}
}