package ru.otus.zarechnev.asapcache.server.service;

import ru.otus.zarechnev.asapcache.starter.AsapCacheDto;

import java.util.Map;

public interface AsapCacheService {
    AsapCacheDto getCachedVal(String cacheName, Integer argumentsHashCode);

    void putToCache(String cacheName, Integer argumentsHashCode, AsapCacheDto value);

    Map<String, Map<Integer, Object>> getCacheContent();
}
