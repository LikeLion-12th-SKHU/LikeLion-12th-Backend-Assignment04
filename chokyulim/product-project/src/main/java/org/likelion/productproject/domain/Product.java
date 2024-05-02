package org.likelion.productproject.domain;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter // 1
@NoArgsConstructor // 2 - 기본 생성자
public class Product { // entity 클래스라고 하기도 한다.

    private Long id; // id 값은 객체 별로 유일해야 하고 변경 불가
    private Long productId;
    private String name;
    private Long price;
    @Builder // 3
    public Product(Long id, Long productId, String name, Long price) {
        this.productId = productId;
        this.id = id;
        this.name = name;
        this.price = price;
    }
    // entity 클래스에서는 setter 대신에 명확히 그 목적과 의도를 나타낼 수 있는 메소드를 추가한다.
    public void update(String name, Long price) {
        this.name = name;
        this.price = price;
    }
}
