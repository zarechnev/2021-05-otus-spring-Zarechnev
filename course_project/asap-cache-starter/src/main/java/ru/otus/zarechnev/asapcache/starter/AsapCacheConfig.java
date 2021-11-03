package ru.otus.zarechnev.asapcache.starter;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@Import(value = {
        AsapCacheAspect.class,
        AsapCacheParams.class,
        AsapCacheRestClient.class
})
public class AsapCacheConfig {
}
