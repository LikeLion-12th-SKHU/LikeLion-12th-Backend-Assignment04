package org.likelion.productproject.domain;

import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Repository //쿼리를 수행하는 메소드
public class ProductRepository {
    private static final Map<Long, Product> database = new HashMap<>();

    public Product save(Product product) {
        database.put(product.getId(), product);
        return database.get(product.getId());
    }

    public Optional<Product> findById(Long id) {
        return Optional.ofNullable(database.get(id));       //null일 수도 있음을 명시 = 다른 방향을 제시
    }

    public List<Product> findAll() {
        return database.values().stream().toList();
    }

    public void deleteById(Long id) {
        database.remove(id);
    }

    public void clear() {
        database.clear();
    }
}