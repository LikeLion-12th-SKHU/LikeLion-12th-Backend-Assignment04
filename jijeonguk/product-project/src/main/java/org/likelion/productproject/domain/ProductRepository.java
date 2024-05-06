package org.likelion.productproject.domain;

import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Repository //데이터베이스 접근 어노테이션 사용
public class ProductRepository {
    private static final Map<Long, Product> database = new HashMap<>();

    public Product save(Product product) {
        database.put(product.getId(), product);
        return database.get(product.getId());
    }

    public Optional<Product> findById(Long id) {
        return Optional.ofNullable(database.get(id));
    }

    //stream()이 뭐였더라?
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
