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

@RequiredArgsConstructor // 1
@Service // 2
public class ProductService {

    private static Long sequence = 0L; // 3
    private final ProductRepository productRepository;

    public Product saveProduct(ProductSaveRequestDto requestDto) {
        Product product = Product.builder().name(requestDto.getName())
                .id(++sequence)
                .productId(requestDto.getProductId())
                .price(requestDto.getPrice()).build();

        return productRepository.save(product);
    }

    public ProductResponseDto findProductById(Long id) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당하는 학생이 없습니다."));

        return ProductResponseDto.from(product);
    }

    public List<ProductResponseDto> findAllProduct() {
        return productRepository.findAll().stream().map(ProductResponseDto::from)
                .collect(Collectors.toList());
    }

    public Product updateProductById(Long id, ProductUpdateRequestDto requestDto) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당하는 학생이 없습니다."));

        product.updateProduct(requestDto.getName(), requestDto.getPrice());

        return productRepository.save(product);
    }

    public void deleteProductById(Long id) {
        productRepository.deleteById(id);
    }
}
