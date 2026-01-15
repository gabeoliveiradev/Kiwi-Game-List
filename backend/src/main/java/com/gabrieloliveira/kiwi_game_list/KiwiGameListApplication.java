package com.gabrieloliveira.kiwi_game_list;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class KiwiGameListApplication {

	public static void main(String[] args) {
		SpringApplication.run(KiwiGameListApplication.class, args);
	}

    @Bean
    public org.springframework.web.client.RestTemplate restTemplate() {
        return new org.springframework.web.client.RestTemplate();
    }
}
