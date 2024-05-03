package com.example.productproject.service;

import lombok.RequiredArgsConstructor;
import com.example.productproject.domain.Product;
import com.example.productproject.domain.ProductRepository;
import com.example.productproject.dto.ProductUpdateRequestDto;
import com.example.productproject.dto.ProductResponseDto;
import com.example.productproject.dto.ProductSaveRequestDto;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collector;

@Service
@RequiredArgsConstructor
public class ProductService {
    private final ProductRepository productRepository;
    private static Long sequance = 0L;

    public Product saveProduct(ProductSaveRequestDto requestDto) {
        Product product = Product.builder()
                .productId(++sequance)
                .productSerialNumber(requestDto.getProductSerialNumber())
                .productPrice(requestDto.getProductPrice())
                .productName(requestDto.getProductName())
                .build();

        return productRepository.save(product);
    }

    public ProductResponseDto findProductById(Long id) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당하는 상품이 없습니다. id = " + id));

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

        product.update(requestDto.getProductName(), requestDto.getPrice());

        return productRepository.save(product);
    }

    public void deleteProductById(Long id) {
        productRepository.deleteById(id);

    }
}
