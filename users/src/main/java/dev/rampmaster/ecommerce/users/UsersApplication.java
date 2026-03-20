package dev.rampmaster.ecommerce.users;

import dev.rampmaster.ecommerce.users.model.UserAccount;
import dev.rampmaster.ecommerce.users.repository.UserAccountRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class UsersApplication {

	public static void main(String[] args) {
		SpringApplication.run(UsersApplication.class, args);
	}

	@Bean
	CommandLineRunner runner(UserAccountRepository repository) {
		return args -> {
			// Clean up previous data
			repository.deleteAll();

			// Create test users
			UserAccount user1 = new UserAccount(20L, "user20", "user20@example.com", "USER", true);
			UserAccount user2 = new UserAccount(50L, "user50", "user50@example.com", "USER", true);

			repository.save(user1);
			repository.save(user2);
		};
	}
}
