//Repository Layer: 데이터 저장소와 직접 연결되는 영역

package org.likelion.productproject.domain;

import org.springframework.stereotype.Repository;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Repository
public class ProductRepository {
    private static final Map<Long, Product> database = new HashMap<>();

    public static Product save(Product product){
        database.put(product.getId(), product);
        return database.get(product.getId());
    }

    public Optional<Product>findById(Long id){
        return Optional.ofNullable(database.get(id));

    }
    public List<Product> findAll(){
        return database.values().stream().toList();
    }
    public void deleteById(Long id){
        database.remove(id);
    }
    public void clear(){
        database.clear();
    }
}
