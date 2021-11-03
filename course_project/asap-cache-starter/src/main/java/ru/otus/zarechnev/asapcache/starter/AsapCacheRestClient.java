package ru.otus.zarechnev.asapcache.starter;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.time.Duration;
import java.time.temporal.ChronoUnit;

@Slf4j
@Service
class AsapCacheRestClient implements AsapCacheClient {

    private static final Duration CLIENT_CONNECT_TIMEOUT = Duration.of(1, ChronoUnit.SECONDS);
    private static final Duration CLIENT_READ_TIMEOUT = Duration.of(1, ChronoUnit.SECONDS);

    private final RestTemplate restTemplate;
    private final AsapCacheParams asapCacheParams;

    public AsapCacheRestClient(final AsapCacheParams asapCacheParams) {
        restTemplate = new RestTemplateBuilder()
                .setConnectTimeout(CLIENT_CONNECT_TIMEOUT)
                .setReadTimeout(CLIENT_READ_TIMEOUT)
                .basicAuthentication(asapCacheParams.getUser(), asapCacheParams.getPass())
                .build();
        this.asapCacheParams = asapCacheParams;
    }

    @Override
    public Object readValue(String cacheName, int argsHashCode) {
        String url = getAsapCacheServerUrl(cacheName, argsHashCode);
        AsapCacheDto dto = restTemplate.getForObject(url, AsapCacheDto.class);
        if (dto != null && dto.getValue() != null) {
            return dto.getValue();
        }
        return null;
    }

    @Override
    public void writeValue(String cacheName, int argsHashCode, Object value) throws JsonProcessingException {
        String url = getAsapCacheServerUrl(cacheName, argsHashCode);
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        String requestJson = new ObjectMapper().writeValueAsString(new AsapCacheDto().setValue(value));
        restTemplate.exchange(url, HttpMethod.POST, new HttpEntity<>(requestJson, headers), Object.class);
    }

    private String getAsapCacheServerUrl(String cacheName, int argsHashCode) {
        return "http://" + asapCacheParams.getHost() + ":" + asapCacheParams.getPort()
                + "/cache/" + cacheName + "/" + argsHashCode;
    }
}
