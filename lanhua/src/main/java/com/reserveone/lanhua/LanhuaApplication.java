package com.reserveone.lanhua;

import com.reserveone.lanhua.config.DotenvConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class LanhuaApplication {
	public static void main(String[] args) {
		DotenvConfig.load();
		SpringApplication.run(LanhuaApplication.class, args);
	}
}