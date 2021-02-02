package fr.lauparr.pplanner.server;

import fr.lauparr.pplanner.server.entities.User;
import fr.lauparr.pplanner.server.security.SpringSecurityAuditorAware;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.web.client.RestTemplate;

@EnableCaching
@EnableJpaAuditing
@SpringBootApplication
public class Application {
	public static void main(final String[] args) {
		SpringApplication.run(Application.class, args);
	}

	@Bean
	public AuditorAware<User> auditorProvider() {
		return new SpringSecurityAuditorAware();
	}

	@Bean
	public RestTemplate restTemplate() {
		return new RestTemplate();
	}
}
