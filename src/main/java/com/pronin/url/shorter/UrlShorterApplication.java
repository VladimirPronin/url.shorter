package com.pronin.url.shorter;

import com.pronin.url.shorter.dao.UrlEntity;
import com.pronin.url.shorter.dao.UrlRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class UrlShorterApplication {

	public static void main(String[] args) {
		SpringApplication.run(UrlShorterApplication.class, args);
	}

	/**
	 * https://spring.io/guides/gs/accessing-data-jpa/
	 * i saw this in example
	 * we can run specific pieces of code when an application is fully started   https://dzone.com/articles/spring-boot-applicationrunner-and-commandlinerunne
	 */
	@Bean
	public CommandLineRunner demo(UrlRepository repository) {
		return (args) -> {
			UrlEntity testEntity = new UrlEntity();
			testEntity.setShortUrl("vova");
			testEntity.setOriginalUrl("https://google.com");
			repository.save(testEntity);
		};
	}
}

