package br.com.edersystems.challenge;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ChallengeApplication {

//    @Bean
//    public WebMvcConfigurer corsConfigurer() {
//        return new WebMvcConfigurer() {
//
//            @Override
//            public void addCorsMappings(CorsRegistry registry) {
//                registry.addMapping("/api").allowedOrigins("http://127.0.0.1:8080");
//            }
//        };
//    }

    public static void main(String[] args) {
        SpringApplication.run(ChallengeApplication.class, args);
    }
}