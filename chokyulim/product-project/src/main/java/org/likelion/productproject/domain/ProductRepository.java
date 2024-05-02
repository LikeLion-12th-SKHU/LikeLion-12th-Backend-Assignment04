package org.likelion.productproject.domain;

import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Repository // 여기 있는 메소드는 쿼리 수행하는 메소드다 라고 인식하게 함
public class ProductRepository {

    private static final Map<Long, Product> database = new HashMap<>();

    public Product save(Product product) {
        database.put(product.getId(), product); // 정보 저장
        return database.get(product.getId());
    }

    public Optional<Product> findById(Long id) {
        return Optional.ofNullable(database.get(id)); // id값 받아서 ???
    }

    public List<Product> findAll() {
        return database.values().stream().toList(); // 전부 찾아 리턴
    }

    public void deleteById(Long id) {
        database.remove(id); // id값을 받으면 삭제
    }

    public void clear() {
        database.clear(); // 데이터베이스 초기화
    }

}
