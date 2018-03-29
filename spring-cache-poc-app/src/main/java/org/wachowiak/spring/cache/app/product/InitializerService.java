package org.wachowiak.spring.cache.app.product;

import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

import static org.wachowiak.spring.cache.app.util.TimeUtils.toDate;

@Service
class InitializerService {

    private final ProductRepository productRepository;

    InitializerService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @PostConstruct
    void init(){
        LocalDate localDate = LocalDate.now();
        LocalTime localTime = LocalTime.now();
        productRepository.save(Product.builder().name("prod 1").added(toDate(LocalDateTime.of(localDate, localTime))).build());
        productRepository.save(Product.builder().name("prod 2").added(toDate(LocalDateTime.of(localDate, localTime.plusMinutes(15)))).build());
        productRepository.save(Product.builder().name("prod 3").added(toDate(LocalDateTime.of(localDate, localTime.plusMinutes(65)))).build());
    }
}
