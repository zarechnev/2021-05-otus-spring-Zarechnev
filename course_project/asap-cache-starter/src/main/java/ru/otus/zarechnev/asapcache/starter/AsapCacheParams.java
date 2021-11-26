package ru.otus.zarechnev.asapcache.starter;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Data
@ConfigurationProperties(prefix = "asap-cache")
public class AsapCacheParams {
    private String host;
    private Integer port;
    private String user;
    private String pass;
}
