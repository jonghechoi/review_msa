package com.springboot.review_msa.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class CorsConfig implements WebMvcConfigurer {
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
//                .allowedOrigins("http://172.26.0.3", "http://172.26.0.1", "http://172.26.0.2")
                .allowedMethods("GET", "POST", "PUT", "DELETE");
    }
}
