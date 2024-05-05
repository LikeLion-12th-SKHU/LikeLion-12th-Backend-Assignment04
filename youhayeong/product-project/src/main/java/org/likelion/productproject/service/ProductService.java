package org.likelion.productproject.service;

import lombok.RequiredArgsConstructor;
import org.likelion.productproject.domain.Product;
import org.likelion.productproject.domain.ProductRepository;
import org.likelion.productproject.dto.ProductResponseDto;
import org.likelion.productproject.dto.ProductSaveRequestDto;
import org.likelion.productproject.dto.ProductUpdateRequestDto;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;
    private static Long sequence = 0L;

    public Product saveProduct(ProductSaveRequestDto requestDto) {  //상품 등록
        Product product = Product.builder()
                .id(++sequence)
                .productId(requestDto.getProductId())       //상품 일련변호
                .name(requestDto.getName())                 //상품명
                .price(requestDto.getPrice())               //가격
                .build();
        return productRepository.save(product);
    }

    public ProductResponseDto findProductById(Long id) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당하는 상품이 없습니다. id = " + id));
        return ProductResponseDto.from(product);
    }

    public List<ProductResponseDto> findAllProduct() {      //상품 전체 조회
        return productRepository.findAll().stream()
                .map(ProductResponseDto::from)
                .collect(Collectors.toList());
    }

    public Product updateProductById(Long id, ProductUpdateRequestDto requestDto) {     //상품 수정
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당하는 상품이 없습니다. id = " + id));

        product.update(requestDto.getName(), requestDto.getPrice());    //상품명, 가격만 수정
        return productRepository.save(product);
    }

    public void deleteProductById(Long id) {       //상품 아이디로 삭제
        productRepository.deleteById(id);
    }

}