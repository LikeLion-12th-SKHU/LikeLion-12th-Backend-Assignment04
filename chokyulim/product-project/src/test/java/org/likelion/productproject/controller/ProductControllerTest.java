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

import static org.assertj.core.api.Assertions.assertThat;
import static org.likelion.productproject.fixture.ProductFixture.*;

@ExtendWith(SpringExtension.class) // Junit5 내에서 스프링 부트 테스트를 사용할 수 있게 해준다.
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT) // 스프링 빈을 사용할 수 있게 환경을 로드하고 랜덤 포트에서 스프링을 실행한다.
@AutoConfigureMockMvc // @Controller, @Service, @Repository Bean을 모두 로드하여 준다.
public class ProductControllerTest {

    @LocalServerPort // 테스트용 랜덤 포트를 주입한다.
    private int port;

    @Autowired // 웹 관련 빈들을 관리하는 빈을 주입
    private WebApplicationContext context;

    @Autowired // 웹 API를 테스트할 때 사용. 가짜 요청을 생성할 수 있다.
    private MockMvc mockMvc;

    @BeforeEach // 한글 깨짐으로 인해 WebApplicationContext의 설정 변경을 통해 UTF-8로 강제 인코딩
    public void setup() {
        mockMvc = MockMvcBuilders.webAppContextSetup(context)
                .addFilter(new CharacterEncodingFilter("UTF-8", true))  // 응답 인코딩을 UTF-8로 강제합니다.
                .build();
    }

    @Autowired
    ObjectMapper objectMapper; // 데이터 직렬화를 위한 객체

    @Autowired
    ProductService productService; // ProductService 주입

    @Autowired
    ProductRepository productRepository; // ProductRepository 주입

    @AfterEach // 각 테스트 종료 이후에 데이터베이스를 초기화
    public void afterEach() {
        productRepository.clear();
    }

    @Test
    @DisplayName("상품을 정상 저장한다")
    public void saveProduct() throws Exception {
        // given
        final ProductSaveRequestDto requestDto = ProductSaveRequestDto.builder()
                .productId(PRODUCT_1.getProductId())
                .name(PRODUCT_1.getName())
                .price(PRODUCT_1.getPrice())
                .build();

        String url = "http://localhost:"+ port + "/products";

        // when
        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.post(url)
                        .content(asJsonString(requestDto))
                        .contentType("application/json"))
                .andReturn();

        // then
        assertThat(mvcResult.getResponse().getStatus()).isEqualTo(HttpStatus.OK.value());

        List<Product> all = productRepository.findAll();
        assertThat(all.get(0).getProductId()).isEqualTo(PRODUCT_1.getProductId());
        assertThat(all.get(0).getName()).isEqualTo(PRODUCT_1.getName());
        assertThat(all.get(0).getPrice()).isEqualTo(PRODUCT_1.getPrice());
    }

    @Test
    @DisplayName("상품을 정상 조회한다")
    public void getProduct() throws Exception {
        // given
        productRepository.save(PRODUCT_2);

        String url = "http://localhost:" + port + "/products/" + PRODUCT_2.getId();

        // when
        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.get(url)).andReturn();

        // then
        final ProductResponseDto productResponse = objectMapper.readValue(
                mvcResult.getResponse().getContentAsString(),
                new TypeReference<>() {
                }
        );

        assertThat(mvcResult.getResponse().getStatus()).isEqualTo(HttpStatus.OK.value());

        assertThat(productResponse.getProductId()).isEqualTo(PRODUCT_2.getProductId());
        assertThat(productResponse.getName()).isEqualTo(PRODUCT_2.getName());
        assertThat(productResponse.getPrice()).isEqualTo(PRODUCT_2.getPrice());
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
    public void updateProduct() throws Exception {
        // given
        final Product savedProduct = productRepository.save(PRODUCT_1);

        Long updateId = savedProduct.getId();
        String newName = "포도";
        Long newPrice = 2500L;

        ProductUpdateRequestDto requestDto = ProductUpdateRequestDto.builder()
                .name(newName)
                .price(newPrice)
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
        assertThat(all.get(0).getName()).isEqualTo(newName);
        assertThat(all.get(0).getPrice()).isEqualTo(newPrice);
    }

    @Test
    @DisplayName("상품을 정상 삭제한다")
    public void deleteProduct() throws Exception {
        // given
        final Product savedProduct = productRepository.save(PRODUCT_1);

        String url = "http://localhost:" + port + "/products/" + savedProduct.getId();

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