package com.agoda.user_service;

import com.agoda.user_service.model.User;
import com.agoda.user_service.model.enums.Active;
import com.agoda.user_service.model.enums.Role;
import com.agoda.user_service.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootApplication
@EnableFeignClients
public class UserServiceApplication implements CommandLineRunner {

	private final UserRepository userRepository;
	private final PasswordEncoder passwordEncoder;

	public UserServiceApplication(UserRepository userRepository, PasswordEncoder passwordEncoder) {
		this.userRepository = userRepository;
		this.passwordEncoder = passwordEncoder;
	}

	public static void main(String[] args) {
		SpringApplication.run(UserServiceApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		final String pass = "$2a$10$2529eBq3R6Y41t03Mku2I.2Nh3W0p25lt.s.85mG0kiAvxI4bsAHa";
		User user = new User();
		user.setEmail("admin@gmail.com");
		user.setPassword(passwordEncoder.encode("Admin123"));
		user.setRole(Role.ADMIN);
		user.setActive(Active.ACTIVE);
		if (!userRepository.existsByEmail("admin@gmail.com")) userRepository.save(user);
	}
}
