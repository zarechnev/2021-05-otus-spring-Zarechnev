package ru.otus.zarechnev.asapcache.starter;

import com.fasterxml.jackson.core.JsonProcessingException;

public interface AsapCacheClient {
    Object readValue(String cacheName, int argsHashCode);

    void writeValue(String cacheName, int argsHashCode, Object ans) throws JsonProcessingException;
}
