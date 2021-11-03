package ru.otus.zarechnev.asapcache.starter;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;
import java.util.Optional;
import java.util.Set;
import java.util.concurrent.CompletableFuture;

@Slf4j
@Aspect
@Component
@RequiredArgsConstructor
public class AsapCacheAspect {

    private final AsapCacheClient asapCacheClient;

    @Around("@annotation(ru.otus.zarechnev.asapcache.starter.AsapCache)")
    public Object asapCacheAroundAspect(final ProceedingJoinPoint joinPoint) throws Throwable {
        log.info("Start aspect");

        String cacheName = getCacheName(joinPoint);
        int argsHashCode = Set.of(joinPoint.getArgs()).hashCode();
        Optional<Object> valueFromCache = readValFromCache(cacheName, argsHashCode);
        if (valueFromCache.isPresent()) {
            log.info("Find needed data in cache");
            return valueFromCache.get();
        }

        log.info("No needed data in cache");

        Object ans = joinPoint.proceed();

        writeValToCache(cacheName, argsHashCode, ans);

        return ans;
    }

    private String getCacheName(JoinPoint joinPoint) {
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        Method method = signature.getMethod();
        AsapCache myAnnotation = method.getAnnotation(AsapCache.class);
        return myAnnotation.name();
    }

    private Optional<Object> readValFromCache(String cacheName, int argsHashCode) {
        try {
            return Optional.ofNullable(asapCacheClient.readValue(cacheName, argsHashCode));
        } catch (Exception e) {
            log.error("Can not read value from cache!", e);
            return Optional.empty();
        }
    }

    private void writeValToCache(String cacheName, int argsHashCode, Object ans) {
        CompletableFuture.runAsync(() -> {
            try {
                asapCacheClient.writeValue(cacheName, argsHashCode, ans);
            } catch (Exception e) {
                log.error("Can not write value to cache!", e);
            }
        });
    }
}
