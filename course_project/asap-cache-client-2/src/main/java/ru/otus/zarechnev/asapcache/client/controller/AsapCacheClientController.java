package ru.otus.zarechnev.asapcache.client.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import ru.otus.zarechnev.asapcache.client.service.AsapCacheClientService;

@RestController
@RequiredArgsConstructor
public class AsapCacheClientController {

    private final AsapCacheClientService asapCacheClientService;

    @GetMapping("/str/{arg}")
    public String indexStr(@PathVariable("arg") String arg) {
        return asapCacheClientService.getData(arg);
    }

    @GetMapping("/int/{arg}")
    public Integer indexInt(@PathVariable("arg") Integer arg) {
        return asapCacheClientService.getData(arg);
    }
}
