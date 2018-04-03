package org.wachowiak.spring.cache.app.demo;

import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.wachowiak.spring.cache.app.util.TimeUtils;

@Controller("/math")
@CacheConfig(cacheNames = "math")
class MathController {

    static final String PI = "3.14";
    static final String E = "2.71";

    @GetMapping("/pi")
    @ResponseBody
    @Cacheable(key = "'pi'")
    public String pi(){
        TimeUtils.sleep(1000);
        return PI;
    }

    @GetMapping("/e")
    @ResponseBody
    @Cacheable(key = "'e'")
    public String e(){
        TimeUtils.sleep(1000);
        return E;
    }
}
