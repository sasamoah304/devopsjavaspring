package com.devopsjavaspring;

import com.devopsjavaspring.backend.persistence.domain.backend.Role;
import com.devopsjavaspring.backend.persistence.domain.backend.User;
import com.devopsjavaspring.backend.persistence.domain.backend.UserRole;
import com.devopsjavaspring.backend.service.UserService;
import com.devopsjavaspring.enums.PlansEnum;
import com.devopsjavaspring.enums.RolesEnum;
import com.devopsjavaspring.utils.UsersUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import java.util.HashSet;
import java.util.Set;

@SpringBootApplication
public class DevopsjavaspringApplication implements CommandLineRunner {

	/** The application logger */
	private static final Logger LOG = LoggerFactory.getLogger(DevopsjavaspringApplication.class);

    @Autowired
	private UserService userService;

	public static void main(String[] args) {

		SpringApplication.run(DevopsjavaspringApplication.class, args);
	}

	@Override
	public void run(String... strings) throws Exception {
		User user = UsersUtils.createBasicUser("sasamoah", "me@com");
		Set<UserRole> userRoles = new HashSet<>();
		userRoles.add(new UserRole(user,new Role(RolesEnum.BASIC)));
		LOG.debug("Creating user with username {}", user.getUsername());
		userService.createUser(user, PlansEnum.PRO, userRoles);
		LOG.info("User {} created", user.getUsername());

	}
}
