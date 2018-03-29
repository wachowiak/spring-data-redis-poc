package org.wachowiak.spring.cache.app.product;

import org.springframework.data.repository.query.Param;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;
import java.util.List;

public interface ProductRepositoryCustom {

    List<Product> findProductByAddedBetween(@Param("from") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date from, @Param("to") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date to);

}
