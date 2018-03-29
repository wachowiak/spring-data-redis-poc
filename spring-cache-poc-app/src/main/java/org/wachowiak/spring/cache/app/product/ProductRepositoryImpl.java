package org.wachowiak.spring.cache.app.product;

import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

class ProductRepositoryImpl implements ProductRepositoryCustom {

    @Autowired
    private ProductRepository productRepository;

    @Override
    public List<Product> findProductByAddedBetween(Date from, Date to) {
        List<Product> result = new ArrayList<>();
        for(Product prod : productRepository.findAll()){
            result.add(prod);
        }
        return result;
    }
}
