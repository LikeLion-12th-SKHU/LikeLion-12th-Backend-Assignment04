package org.likelion.productproject.controller;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.likelion.productproject.domain.Item;
import org.likelion.productproject.domain.ItemRepository;
import org.likelion.productproject.dto.ItemResponseDto;
import org.likelion.productproject.dto.ItemSaveRequestDto;
import org.likelion.productproject.dto.ItemUpdateRequestDto;
import org.likelion.productproject.service.ItemService;
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
import static org.likelion.productproject.fixture.ItemFixture.*;

@ExtendWith(SpringExtension.class) // Junit5 내에서 스프링 부트 테스트를 사용할 수 있게 해준다.
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT) // 스프링 빈을 사용할 수 있게 환경을 로드하고 랜덤 포트에서 스프링을 실행한다.
@AutoConfigureMockMvc // @Controller, @Service, @Repository Bean을 모두 로드하여 준다.
public class ItemControllerTest {

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
    ItemService itemService; // StudentService 주입

    @Autowired
    ItemRepository itemRepository; // StudentRepository 주입

    @AfterEach // 각 테스트 종료 이후에 데이터베이스를 초기화
    public void afterEach() {
        itemRepository.clear();
    }

    @Test
    @DisplayName("물품을 정상 저장한다")
    public void saveItem() throws Exception {
        // given
        final ItemSaveRequestDto requestDto = ItemSaveRequestDto.builder()
                .itemId(ITEM_1.getItemId())
                .name(ITEM_1.getName())
                .price(ITEM_1.getPrice())
                .build();

        String url = "http://localhost:"+ port + "/Item";

        // when
        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.post(url)
                        .content(asJsonString(requestDto))
                        .contentType("application/json"))
                .andReturn();

        // then
        assertThat(mvcResult.getResponse().getStatus()).isEqualTo(HttpStatus.OK.value());

        List<Item> all = itemRepository.findAll();
        assertThat(all.get(0).getItemId()).isEqualTo(ITEM_1.getItemId());
        assertThat(all.get(0).getName()).isEqualTo(ITEM_1.getName());
        assertThat(all.get(0).getPrice()).isEqualTo(ITEM_1.getPrice());
    }

    @Test
    @DisplayName("물품을 정상 조회한다")
    public void getStudent() throws Exception {
        // given
        itemRepository.save(ITEM_2);

        String url = "http://localhost:" + port + "/items/" + ITEM_2.getId();

        // when
        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.get(url)).andReturn();

        // then
        final ItemResponseDto itemResponse = objectMapper.readValue(
                mvcResult.getResponse().getContentAsString(),
                new TypeReference<>() {
                }
        );

        assertThat(mvcResult.getResponse().getStatus()).isEqualTo(HttpStatus.OK.value());

        assertThat(itemResponse.getItemId()).isEqualTo(ITEM_2.getItemId());
        assertThat(itemResponse.getName()).isEqualTo(ITEM_2.getName());
        assertThat(itemResponse.getPrice()).isEqualTo(ITEM_2.getPrice());
    }


    @Test
    @DisplayName("물품 전체를 정상 조회한다")
    public void getAllItem() throws Exception {
        // given
        itemRepository.save(ITEM_1);
        itemRepository.save(ITEM_2);
        itemRepository.save(ITEM_3);

        String url = "http://localhost:" + port + "/items";

        // when
        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.get(url)).andReturn();

        // then
        final List<ItemResponseDto> actualResponses = objectMapper.readValue(
                mvcResult.getResponse().getContentAsString(),
                new TypeReference<>() {
                }
        );

        List<ItemResponseDto> expectedResponses = itemService.findAllItem();

        assertThat(actualResponses).usingRecursiveComparison().isEqualTo(expectedResponses);
    }

    @Test
    @DisplayName("물품을 정상 수정한다")
    public void updateItem() throws Exception {
        // given
        final Item savedItem = itemRepository.save(ITEM_1);

        Long updateId = savedItem.getId();
        String newName = "새우깡";
        Long newPrice = 1360L;

        ItemUpdateRequestDto requestDto = ItemUpdateRequestDto.builder()
                .name(newName)
                .price(newPrice)
                .build();

        String url = "http://localhost:" + port + "/items/" + updateId;

        // when
        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.patch(url)
                        .content(asJsonString(requestDto))
                        .contentType("application/json"))
                .andReturn();

        // then
        assertThat(mvcResult.getResponse().getStatus()).isEqualTo(HttpStatus.OK.value());

        List<Item> all = itemRepository.findAll();
        assertThat(all.get(0).getName()).isEqualTo(newName);
        assertThat(all.get(0).getPrice()).isEqualTo(newPrice);
    }

    @Test
    @DisplayName("물품을 정상 삭제한다")
    public void deleteItem() throws Exception {
        // given
        final Item savedItem = itemRepository.save(ITEM_1);

        String url = "http://localhost:" + port + "/item/" + savedItem.getId();

        // when
        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.delete(url)).andReturn();

        // then
        assertThat(mvcResult.getResponse().getStatus()).isEqualTo(HttpStatus.OK.value());

        List<Item> all = itemRepository.findAll();
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