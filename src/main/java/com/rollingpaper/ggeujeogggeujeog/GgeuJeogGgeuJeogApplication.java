package com.rollingpaper.ggeujeogggeujeog;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.session.data.redis.config.annotation.web.http.EnableRedisHttpSession;

@EnableRedisHttpSession
@SpringBootApplication(exclude = SecurityAutoConfiguration.class)
public class GgeuJeogGgeuJeogApplication {

	public static void main(String[] args) {
		SpringApplication.run(GgeuJeogGgeuJeogApplication.class, args);
	}

}
