package ru.otus.zarechnev.asapcache.client.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.otus.zarechnev.asapcache.starter.AsapCache;

@Slf4j
@Service
class AsapCacheClientServiceImpl implements AsapCacheClientService {

    @Override
    @AsapCache(name = "getDataStr")
    public String getData(String arg) {
        log.debug("Start getData method with arg={}", arg);

        try {
            Thread.sleep(7000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        log.debug("End getData method with arg={}", arg);

        return arg.toUpperCase();
    }

    @Override
    @AsapCache(name = "getDataInt")
    public Integer getData(Integer arg) {
        log.debug("Start getData method with arg={}", arg);

        try {
            Thread.sleep(7000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        log.debug("End getData method with arg={}", arg);

        return arg;
    }
}
