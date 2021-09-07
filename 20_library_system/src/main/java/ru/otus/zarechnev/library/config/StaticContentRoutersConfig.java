package ru.otus.zarechnev.library.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;

@Configuration
public class StaticContentRoutersConfig {

    @Bean
    public RouterFunction<ServerResponse> cssRouter() {
        return RouterFunctions.resources("/css/**", new ClassPathResource("static/css/"));
    }

    @Bean
    public RouterFunction<ServerResponse> fontRouter() {
        return RouterFunctions.resources("/font/**", new ClassPathResource("static/font/"));
    }

    @Bean
    public RouterFunction<ServerResponse> imagesRouter() {
        return RouterFunctions.resources("/images/**", new ClassPathResource("static/images/"));
    }

    @Bean
    public RouterFunction<ServerResponse> jsRouter() {
        return RouterFunctions.resources("/js/**", new ClassPathResource("static/js/"));
    }
}
