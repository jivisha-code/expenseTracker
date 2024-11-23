package com.project.lms.ExpenseTrackerLms.configurations;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebMvcConfigurerWebConfig implements WebMvcConfigurer {
        @Override
        public void addCorsMappings(CorsRegistry registry) {
            registry.addMapping("/**") // Applies CORS to all endpoints
                    .allowedOrigins("http://localhost:4200") // Allow Angular frontend
                    .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS") // Allow HTTP methods
                    .allowedHeaders("*") // Allow all headers
                    .allowCredentials(true); // Allow cookies/authentication headers
        }
}
