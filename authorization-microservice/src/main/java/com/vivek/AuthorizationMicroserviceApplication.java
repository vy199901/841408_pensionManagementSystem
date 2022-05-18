package com.vivek;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

import com.vivek.model.User;
import com.vivek.repository.UserRepository;

@SpringBootApplication
@EnableFeignClients
public class AuthorizationMicroserviceApplication {

	private UserRepository repository;
	
	
	@Autowired
	public AuthorizationMicroserviceApplication(UserRepository repository) {
		this.repository = repository;
	}

	@PostConstruct
	public void initUser() {
		List<User> users = Stream.of(new User(101, "Admin", "admin"), new User(102, "vivek", "yadav")

		).collect(Collectors.toList());
		repository.saveAll(users);
	}

	public static void main(String[] args) {
		SpringApplication.run(AuthorizationMicroserviceApplication.class, args);
	}

}
