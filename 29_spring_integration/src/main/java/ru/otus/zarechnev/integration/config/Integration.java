package ru.otus.zarechnev.integration.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.integration.channel.DirectChannel;
import org.springframework.integration.config.EnableIntegration;
import org.springframework.integration.dsl.IntegrationFlow;
import org.springframework.integration.dsl.IntegrationFlows;
import org.springframework.integration.dsl.MessageChannels;

@Configuration
@EnableIntegration
public class Integration {

    @Bean
    public DirectChannel fromManToLibraryChannel() {
        return MessageChannels.direct().get();
    }

    @Bean
    public DirectChannel fromLibraryToManChannel() {
        return MessageChannels.direct().get();
    }

    @Bean
    public IntegrationFlow fromManToLibraryFlow() {
        return IntegrationFlows
                .from(fromManToLibraryChannel())
                .handle("libraryServiceImpl", "getNextBook")
                .channel(fromLibraryToManChannel())
                .get();
    }

    @Bean
    public IntegrationFlow fromLibraryToManFlow() {
        return IntegrationFlows
                .from(fromLibraryToManChannel())
                .handle("readerManImpl", "onGettingBook")
                .channel(fromManToLibraryChannel())
                .get();
    }
}
