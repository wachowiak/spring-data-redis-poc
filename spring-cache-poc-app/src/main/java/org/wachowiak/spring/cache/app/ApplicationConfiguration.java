package org.wachowiak.spring.cache.app;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@Import(CacheClientConfiguration.class)
class ApplicationConfiguration { }
