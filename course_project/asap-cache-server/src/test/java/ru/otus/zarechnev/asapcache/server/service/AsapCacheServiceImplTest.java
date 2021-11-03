package ru.otus.zarechnev.asapcache.server.service;

import org.junit.jupiter.api.Test;
import ru.otus.zarechnev.asapcache.starter.AsapCacheDto;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class AsapCacheServiceImplTest {

    @Test
    void toStringTest() {
        AsapCacheService asapCacheService = new AsapCacheServiceImpl();
        asapCacheService.putToCache("name", 12, new AsapCacheDto().setValue(List.of(3, 5)));
        asapCacheService.putToCache("name2", 14, new AsapCacheDto().setValue("SDSDF"));

        assertEquals("{name={12=[3, 5]}, name2={14=SDSDF}}", asapCacheService.getCacheContent().toString());
    }
}