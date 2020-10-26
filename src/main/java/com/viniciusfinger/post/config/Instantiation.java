package com.viniciusfinger.post.config;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.TimeZone;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

import com.viniciusfinger.post.domain.Post;
import com.viniciusfinger.post.domain.User;
import com.viniciusfinger.post.dto.AuthorDTO;
import com.viniciusfinger.post.dto.CommentDTO;
import com.viniciusfinger.post.repositories.PostRepository;
import com.viniciusfinger.post.repositories.UserRepository;

@Configuration
public class Instantiation implements CommandLineRunner {

	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private PostRepository postRepository;
	
	@Override
	public void run(String... args) throws Exception {
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		sdf.setTimeZone(TimeZone.getTimeZone("GMT"));
		
		postRepository.deleteAll();
		userRepository.deleteAll();
		
		User maria = new User(null, "Maria Brown", "maria@gmail.com");
		User alex = new User(null, "Alex Green", "alex@gmail.com");
		User bob = new User(null, "Bob Grey", "bob@gmail.com");
		
		userRepository.saveAll(Arrays.asList(maria, alex, bob));
		
		Post post1 = new Post(null, sdf.parse("21/03/2020"), "Partiu viagem", "vou viajar para são paulo, abraços", new AuthorDTO(maria));
		Post post2 = new Post(null, sdf.parse("30/10/2020"), "Hoje é meu aniversário", "30 de outubro faço 19 anos.", new AuthorDTO(bob));
		
		CommentDTO c1 = new CommentDTO(sdf.parse("21/03/2020"), "Boa viagem.", new AuthorDTO(alex));
		CommentDTO c2 = new CommentDTO(sdf.parse("22/03/2020"), "Aproveite.", new AuthorDTO(bob));
		CommentDTO c3 = new CommentDTO(sdf.parse("30/10/2020"), "Parabéns!", new AuthorDTO(maria));
		
		post1.getComments().addAll(Arrays.asList(c1,c2));
		post2.getComments().addAll(Arrays.asList(c3));
		
		postRepository.saveAll(Arrays.asList(post1,post2));
		
		maria.getPosts().add(post1);
		bob.getPosts().add(post2);
		
		userRepository.saveAll(Arrays.asList(maria, bob));
	}
}
