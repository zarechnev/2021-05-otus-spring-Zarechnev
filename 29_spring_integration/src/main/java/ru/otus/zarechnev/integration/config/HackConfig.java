package ru.otus.zarechnev.integration.config;

import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.support.MessageBuilder;
import ru.otus.zarechnev.integration.domain.Book;

@Configuration
public class HackConfig {

    @Bean
    public ApplicationRunner runner(MessageChannel fromManToLibraryChannel) {
        return arg -> fromManToLibraryChannel.send(
                MessageBuilder.withPayload(new Book().setId(0L).setTitle("Last Witch"))
                        .build()
        );
    }
}
