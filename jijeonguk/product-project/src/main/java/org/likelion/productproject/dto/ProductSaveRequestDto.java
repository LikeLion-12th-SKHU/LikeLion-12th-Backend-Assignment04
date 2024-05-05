package org.likelion.productproject.dto;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

//Dto의 역할은 Entity클래스는 데이터베이스와 맞닿은 핵심 클래스라 해당 클래스를 요청,응답으로 사용하면 안된다.
@Getter
@NoArgsConstructor
public class ProductSaveRequestDto {
    private Long product_id;
    private String name;
    private Long price;

    @Builder
    public ProductSaveRequestDto(Long product_id, String name, Long price) {
        this.product_id = product_id;
        this.name = name;
        this.price = price;
    }
}
