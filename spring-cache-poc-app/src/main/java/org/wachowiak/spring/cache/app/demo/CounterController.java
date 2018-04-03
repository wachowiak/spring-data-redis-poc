package org.wachowiak.spring.cache.app.demo;

import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.wachowiak.spring.cache.app.util.TimeUtils;

import java.util.concurrent.ConcurrentHashMap;

@Controller("/counter")
@CacheConfig(cacheNames = "counter")
class CounterController {

    private final ConcurrentHashMap<String, Long> items = new ConcurrentHashMap<>();

    @PostMapping("/inc/{key}")
    @ResponseBody
    @CacheEvict(key = "'get_' + #key")
    public Long inc(@PathVariable("key") String key){
        items.compute(key, (k, v) -> v == null ? 1 : v + 1);
        return items.get(key);
    }

    @GetMapping("/get/{key}")
    @ResponseBody
    @Cacheable(key = "'get_' + #key")
    public Long get(@PathVariable("key") String key){
        TimeUtils.sleep(1000);
        return items.get(key);
    }

}

