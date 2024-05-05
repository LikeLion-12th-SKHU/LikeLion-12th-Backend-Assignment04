package org.likelion.productproject.controller;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.likelion.productproject.domain.Product;
import org.likelion.productproject.domain.ProductRepository;
import org.likelion.productproject.dto.ProductResponseDto;
import org.likelion.productproject.dto.ProductSaveRequestDto;
import org.likelion.productproject.dto.ProductUpdateRequestDto;
import org.likelion.productproject.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.filter.CharacterEncodingFilter;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.likelion.productproject.fixture.ProductStand.*;

@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
public class ProductControllerTest {
    @Autowired
    ObjectMapper objectMapper;
    @Autowired
    ProductService productService;
    @Autowired
    ProductRepository productRepository;
    @Autowired
    private WebApplicationContext context;
    @Autowired
    private MockMvc mockMvc;

    @BeforeEach
    public void setup() {
        mockMvc = MockMvcBuilders.webAppContextSetup(context)
                .addFilter(new CharacterEncodingFilter("UTF-8", true)).build();
    }

    @AfterEach
    public void afterEach() {
        productRepository.clear();
    }

    @Test
    @DisplayName("물품을 저장한다.")
    public void saveProduct() throws Exception {
        //given
        final ProductSaveRequestDto requestDto = ProductSaveRequestDto.builder()
                .name(PRODUCT1.getName())
                .productId(PRODUCT1.getProductId())
                .price(PRODUCT1.getPrice())
                .build();

        String url = "http://localhost:8080/products";

        //when
        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.post(url)
                        .content(asJsonString(requestDto)).contentType("application/json"))
                .andReturn();

        //Then
        assertThat(mvcResult.getResponse().getStatus()).isEqualTo(HttpStatus.OK.value());

        List<Product> allProduct = productRepository.findAll();
        assertThat(allProduct.get(0).getProductId()).isEqualTo(PRODUCT1.getProductId());
        assertThat(allProduct.get(0).getPrice()).isEqualTo(PRODUCT1.getPrice());
        assertThat(allProduct.get(0).getName()).isEqualTo(PRODUCT1.getName());
    }

    @Test
    @DisplayName("물품을 조회한다")
    public void getProduct() throws Exception {
        //Given
        productRepository.save(PRODUCT2);
        String url = "http://localhost:8080/products/" + PRODUCT2.getId();

        //When
        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.get(url)).andReturn();

        //Then
        final ProductResponseDto productResponse = objectMapper.readValue(
                mvcResult.getResponse().getContentAsString(),
                new TypeReference<>() {
                }
        );
        assertThat(mvcResult.getResponse().getStatus()).isEqualTo(HttpStatus.OK.value());

        assertThat(productResponse.getName()).isEqualTo(PRODUCT2.getName());
        assertThat(productResponse.getProductId()).isEqualTo(PRODUCT2.getProductId());
        assertThat(productResponse.getPrice()).isEqualTo(PRODUCT2.getPrice());
    }

    @Test
    @DisplayName("물품 전체를 조회한다")
    public void getAllProduct() throws Exception {
        //Given
        productRepository.save(PRODUCT1);
        productRepository.save(PRODUCT2);
        productRepository.save(PRODUCT3);
        String url = "http://localhost:8080/products";

        //When
        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.get(url)).andReturn();

        //Then
        final List<ProductResponseDto> actualResponses = objectMapper
                .readValue(mvcResult.getResponse().getContentAsString(),
                        new TypeReference<>() {
                }
        );

        List<ProductResponseDto> expectedResponses = productService.findAllProduct();
        assertThat(actualResponses).usingRecursiveComparison().isEqualTo(expectedResponses);
    }

    @Test
    @DisplayName("물품을 수정한다.")
    public void updateProduct() throws Exception {
        //Given
        final Product savedProduct = productRepository.save(PRODUCT1);

        Long updateId = savedProduct.getId();
        String newName = "토마토";
        Long newPrice = 1750L;

        ProductUpdateRequestDto requestDto = ProductUpdateRequestDto.builder()
                .name(newName)
                .price(newPrice)
                .build();
        String url = "http://localhost:8080/products/" + updateId;

        //When
        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.patch(url)
                .content(asJsonString(requestDto)).contentType("application/json"))
                .andReturn();

        //Then
        assertThat(mvcResult.getResponse().getStatus()).isEqualTo(HttpStatus.OK.value());

        List<Product> allProduct = productRepository.findAll();
        assertThat(allProduct.get(0).getName()).isEqualTo(newName);
        assertThat(allProduct.get(0).getPrice()).isEqualTo(newPrice);
    }
    @Test
    @DisplayName("물품을 삭제한다.")
    public void deleteProduct() throws Exception {
        //Given
        final Product savedProduct = productRepository.save(PRODUCT1);
        String url = "http://localhost:8080/products/" + savedProduct.getId();

        //When
        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.delete(url)).andReturn();

        //Then
        assertThat(mvcResult.getResponse().getStatus()).isEqualTo(HttpStatus.OK.value());

        List<Product> allProduct = productRepository.findAll();
        assertThat(allProduct.size()).isEqualTo(0);
    }

    private String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception exception1) {
            throw new RuntimeException(exception1);
        }
    }
}
