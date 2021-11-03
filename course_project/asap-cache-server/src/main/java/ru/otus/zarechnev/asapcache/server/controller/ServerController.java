package ru.otus.zarechnev.asapcache.server.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.otus.zarechnev.asapcache.server.service.AsapCacheService;
import ru.otus.zarechnev.asapcache.starter.AsapCacheDto;

@RestController
@RequiredArgsConstructor
public class ServerController {

    private final AsapCacheService asapCacheService;

    @GetMapping(path = "/cache")
    public Object getCacheContent() {
        return asapCacheService.getCacheContent();
    }

    @GetMapping(path = "/cache/{cacheName}/{argumentsHashCode}")
    public AsapCacheDto getCachedVal(
            @PathVariable("cacheName") String cacheName,
            @PathVariable("argumentsHashCode") Integer argumentsHashCode
    ) {
        return asapCacheService.getCachedVal(cacheName, argumentsHashCode);
    }

    @PostMapping(path = "/cache/{cacheName}/{argumentsHashCode}")
    public void setCachedVal(
            @PathVariable("cacheName") String cacheName,
            @PathVariable("argumentsHashCode") Integer argumentsHashCode,
            @RequestBody AsapCacheDto value
    ) {
        asapCacheService.putToCache(cacheName, argumentsHashCode, value);
    }
}
