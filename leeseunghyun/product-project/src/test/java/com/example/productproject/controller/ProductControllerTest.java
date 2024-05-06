package com.example.productproject.controller;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import com.example.productproject.domain.Product;
import com.example.productproject.domain.ProductRepository;
import com.example.productproject.dto.ProductResponseDto;
import com.example.productproject.dto.ProductSaveRequestDto;
import com.example.productproject.dto.ProductUpdateRequestDto;
import com.example.productproject.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.filter.CharacterEncodingFilter;

import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static com.example.productproject.fixture.ProductFixture.*;

@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
public class ProductControllerTest {

    @LocalServerPort // 테스트용 랜덤 포트를 주입한다.
    private int port;

    @Autowired  // 웹 관련 빈들을 관리하는 빈을 주입
    private WebApplicationContext context;

    @Autowired // 웹 API를 테스트할 때 사용. 가짜 요청을 생성할 수 있다.
    private MockMvc mockMvc;

    @BeforeEach // 한글 깨짐으로 인해 WebApplicationContext의 설정 변경을 통해 UTF-8로 강제 인코딩
    public void setup() {
        mockMvc = MockMvcBuilders.webAppContextSetup(context)
                .addFilter(new CharacterEncodingFilter("UTF-8", true))
                .build();
    }

    @Autowired
    ObjectMapper objectMapper;

    @Autowired
    ProductService productService;

    @Autowired
    ProductRepository productRepository;

    @AfterEach
    public void afterEach() {
        productRepository.clear();
    }

    @Test
    @DisplayName("상품을 정상 저장한다")
    public void saveProduct() throws Exception {
        // given
        final ProductSaveRequestDto requestDto = ProductSaveRequestDto.builder()
                .productSerialNumber(PRODUCT_1.getProductSerialNumber())
                .productName(PRODUCT_1.getProductName())
                .productPrice(PRODUCT_1.getProductPrice())
                .build();

        String url = "http://localhost:" + port + "/products";

        // when
        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.post(url)
                        .content(asJsonString(requestDto))
                        .contentType("application/json"))
                .andReturn();

        // then
        assertThat(mvcResult.getResponse().getStatus()).isEqualTo(HttpStatus.OK.value());

        List<Product> all = productRepository.findAll();
        assertThat(all.get(0).getProductSerialNumber()).isEqualTo(PRODUCT_1.getProductId());
        assertThat(all.get(0).getProductName()).isEqualTo(PRODUCT_1.getProductName());
        assertThat(all.get(0).getProductPrice()).isEqualTo(PRODUCT_1.getProductPrice());
    }

    @Test
    @DisplayName("상품을 정상 조회한다")
    public void getProduct() throws Exception {
        // given
        productRepository.save(PRODUCT_2);

        String url = "http://localhost:" + port + "/products/" + PRODUCT_2.getProductId();

        // when
        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.get(url)).andReturn();

        // then
        final ProductResponseDto productResponse = objectMapper.readValue(
                mvcResult.getResponse().getContentAsString(),
                new TypeReference<>() {
                }
        );

        assertThat(mvcResult.getResponse().getStatus()).isEqualTo(HttpStatus.OK.value());

        assertThat(productResponse.getProductSerialNumber()).isEqualTo(PRODUCT_2.getProductId());
        assertThat(productResponse.getProductName()).isEqualTo(PRODUCT_2.getProductName());
        assertThat(productResponse.getProductPrice()).isEqualTo(PRODUCT_2.getProductPrice());
    }

    @Test
    @DisplayName("상품 전체를 정상 조회한다")
    public void getAllProduct() throws Exception {
        // given
        productRepository.save(PRODUCT_1);
        productRepository.save(PRODUCT_2);
        productRepository.save(PRODUCT_3);

        String url = "http://localhost:" + port + "/products";

        // when
        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.get(url)).andReturn();

        // then
        final List<ProductResponseDto> actualResponses = objectMapper.readValue(
                mvcResult.getResponse().getContentAsString(),
                new TypeReference<>() {
                }
        );

        List<ProductResponseDto> expectedResponses = productService.findAllProduct();
        assertThat(actualResponses).usingRecursiveComparison().isEqualTo(expectedResponses);
    }

    @Test
    @DisplayName("상품을 정상 수정한다")
    public void updateStudent() throws Exception {
        // given
        final Product savedProduct = productRepository.save(PRODUCT_1);

        Long updateId = savedProduct.getProductId();
        String newName = "닥터페퍼";
        Long newPrice = 1500L;

        ProductUpdateRequestDto requestDto = ProductUpdateRequestDto.builder()
                .productName(newName)
                .productPrice(newPrice)
                .build();

        String url = "http://localhost:" + port + "/products/" + updateId;

        // when
        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.patch(url)
                        .content(asJsonString(requestDto))
                        .contentType("application/json"))
                .andReturn();

        // then
        assertThat(mvcResult.getResponse().getStatus()).isEqualTo(HttpStatus.OK.value());

        List<Product> all = productRepository.findAll();
        assertThat(all.get(0).getProductName()).isEqualTo(newName);
        assertThat(all.get(0).getProductPrice()).isEqualTo(newPrice);
    }

    @Test
    @DisplayName("상품을 정상 삭제한다")
    public void deleteProduct() throws Exception {
        // given
        final Product savedProduct = productRepository.save(PRODUCT_1);

        String url = "http://localhost:" + port + "/products/" + savedProduct.getProductId();

        // when
        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.delete(url)).andReturn();

        // then
        assertThat(mvcResult.getResponse().getStatus()).isEqualTo(HttpStatus.OK.value());

        List<Product> all = productRepository.findAll();
        assertThat(all.size()).isEqualTo(0);
    }

    private String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
