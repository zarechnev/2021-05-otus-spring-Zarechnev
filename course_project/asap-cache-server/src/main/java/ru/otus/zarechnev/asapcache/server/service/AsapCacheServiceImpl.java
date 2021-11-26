package ru.otus.zarechnev.asapcache.server.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.otus.zarechnev.asapcache.starter.AsapCacheDto;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

@Slf4j
@Service
class AsapCacheServiceImpl implements AsapCacheService {

    private final Map<String, Map<Integer, Object>> cachedValues = Collections.synchronizedMap(new HashMap<>());

    @Override
    public AsapCacheDto getCachedVal(String cacheName, Integer argumentsHashCode) {
        log.info("Try get val from cache={} by argsHashCode={}", cacheName, argumentsHashCode);

        if (cachedValues.containsKey(cacheName) && cachedValues.get(cacheName).containsKey(argumentsHashCode)) {
            return new AsapCacheDto().setValue(cachedValues.get(cacheName).get(argumentsHashCode));
        }

        return new AsapCacheDto();
    }

    @Override
    public void putToCache(String cacheName, Integer argumentsHashCode, AsapCacheDto asapCacheDto) {
        log.info("Try put val={} to cache={} by argsHashCode={}", asapCacheDto.getValue(), cacheName, argumentsHashCode);

        if (cachedValues.containsKey(cacheName)) {
            cachedValues.get(cacheName).put(argumentsHashCode, asapCacheDto.getValue());
            return;
        }

        HashMap<Integer, Object> map = new HashMap<>();
        map.put(argumentsHashCode, asapCacheDto.getValue());
        cachedValues.put(cacheName, map);
    }

    @Override
    public Map<String, Map<Integer, Object>> getCacheContent() {
        return cachedValues;
    }
}
