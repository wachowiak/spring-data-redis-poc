package org.wachowiak.spring.cache.app.product;

import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.rest.core.annotation.RestResource;
import org.wachowiak.spring.cache.app.util.TimeUtils;

@RepositoryRestResource(path = "products", collectionResourceRel = "products")
@CacheConfig(cacheNames = "product")
interface ProductRepository extends CrudRepository<Product, Long> {

    @Override
    @Cacheable
    default Iterable<Product> findAll(){
        TimeUtils.sleep(1000);
        return findBy();
    }

    @RestResource(exported = false)
    Iterable<Product> findBy();

//    @Query("select p from Product p where p.added >= ?1 and p.added < ?2")
//    List<Product> findProductByAddedBetween(@Param("from") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date from, @Param("to") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date to);
}
