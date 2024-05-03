package com.example.productproject.controller;

import com.example.productproject.dto.ProductResponseDto;
import com.example.productproject.dto.ProductSaveRequestDto;
import com.example.productproject.dto.ProductUpdateRequestDto;
import com.example.productproject.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
public class ProductController {

    private final ProductService productService;

    @PostMapping("/products")
    public void save(@RequestBody ProductSaveRequestDto requestDto) {
        productService.saveProduct(requestDto);
    }

    @GetMapping("/products/{id}")
    public ProductResponseDto findProductById(@PathVariable Long id) {
        return productService.findProductById(id);
    }

    @GetMapping("/products")
    public List<ProductResponseDto> findAllProduct() {
        return productService.findAllProduct();
    }

    @PatchMapping("/products/{id}")
    public void updateProductById(@PathVariable Long id, @RequestBody ProductUpdateRequestDto requestDto) {
        productService.updateProductById(id, requestDto);
    }

    @DeleteMapping("/products/{id}")
    public void deleteProductById(@PathVariable Long id) {
        productService.deleteProductById(id);
    }
}
