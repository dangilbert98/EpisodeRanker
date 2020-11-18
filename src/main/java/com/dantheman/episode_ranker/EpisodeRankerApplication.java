package com.dantheman.episode_ranker;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
public class EpisodeRankerApplication {

    public static void main(String[] args) {
        SpringApplication.run(EpisodeRankerApplication.class, args);
    }

    @Bean
    public RestTemplate episodesRestTemplate(RestTemplateBuilder builder) {
        return builder.build();
    }
}
