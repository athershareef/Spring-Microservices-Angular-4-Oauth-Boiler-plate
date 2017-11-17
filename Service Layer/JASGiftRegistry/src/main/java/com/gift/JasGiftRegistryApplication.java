package com.gift;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.SecurityAutoConfiguration;
import org.springframework.boot.autoconfigure.security.oauth2.client.EnableOAuth2Sso;

@SpringBootApplication(exclude = SecurityAutoConfiguration.class)
@EnableOAuth2Sso
public class JasGiftRegistryApplication {

	public static void main(String[] args) {
		SpringApplication.run(JasGiftRegistryApplication.class, args);
	}

	// @Autowired
	// private IUserService userService;
	//
	// @Bean
	// public CommandLineRunner setup(UserRepository userRepository) {
	// return (args) -> {
	//
	// User user = new User();
	// user.setUsername("ather");
	// user.setEmail("ather@gmail.com");
	// user.setPassword(new BCryptPasswordEncoder().encode("p"));
	// Set<UserRole> userRoles = new HashSet<>();
	// Role userRole = new Role();
	// userRole.setName("ROLE_USER");
	// userRoles.add(new UserRole(user, userRole));
	//
	// userService.createUser(user, userRoles);
	//
	// userRoles.clear();
	//
	// User admin = new User();
	// admin.setUsername("admin");
	// admin.setEmail("admin@gmail.com");
	// admin.setPassword(new BCryptPasswordEncoder().encode("p"));
	// Role adminRole = new Role();
	// adminRole.setName("ROLE_ADMIN");
	// userRoles.add(new UserRole(admin, adminRole));
	//
	// userService.createUser(admin, userRoles);
	// };
	// }
}
