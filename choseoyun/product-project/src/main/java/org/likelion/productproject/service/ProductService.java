package org.likelion.productproject.service;

import lombok.RequiredArgsConstructor;
import org.likelion.productproject.domain.Product;
import org.likelion.productproject.domain.ProductRepository;
import org.likelion.productproject.dto.ProductSaveRequestDto;
import  org.likelion.productproject.dto.ProductResponseDto;
import org.likelion.productproject.dto.ProductUpdateRequestDto;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;

    private static Long sequence = 0L;

    public Product saveProduct(ProductSaveRequestDto requestDto) {
        Product product = Product.builder()
                .id(++sequence)
                .productId(requestDto.getProductId())
                .name(requestDto.getName())
                .price(requestDto.getPrice())
                .build();

        return ProductRepository.save(product);
    }

    public ProductResponseDto findProductById(Long id) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당하는 물품이 없습니다. id = " + id));

        return ProductResponseDto.from(product);

    }

    public List<ProductResponseDto> findAllProduct() {
        return productRepository.findAll().stream()
                .map(ProductResponseDto::from)
                .collect(Collectors.toList());
    }

    public Product updateProductById(Long id, ProductUpdateRequestDto requestDto) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당하는 상품이 없습니다. id = " + id));

        product.update(requestDto.getName(), requestDto.getPrice());

        return ProductRepository.save(product);
    }

    public void deleteProductById(Long id) {
        productRepository.deleteById(id);
    }
}
