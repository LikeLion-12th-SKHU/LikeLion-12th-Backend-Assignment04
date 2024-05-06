package org.likelion.productproject.service;

import lombok.RequiredArgsConstructor;
import org.likelion.productproject.domain.Item;
import org.likelion.productproject.domain.ItemRepository;
import org.likelion.productproject.dto.ItemResponseDto;
import org.likelion.productproject.dto.ItemSaveRequestDto;
import org.likelion.productproject.dto.ItemUpdateRequestDto;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class ItemService {

    private final ItemRepository itemRepository;

    private static Long sequence = 0L;

    public Item saveItem(ItemSaveRequestDto requestDto) {
        Item item = Item.builder()
                .id(++sequence)
                .itemId(requestDto.getItemId())
                .name(requestDto.getName())
                .price(requestDto.getPrice())
                .build();

        return itemRepository.save(item);
    }

    public ItemResponseDto findItemById(Long id) {
        Item item = ItemRepository.findById(id)
                .orElseThrow(()-> new IllegalArgumentException("해당하는 물품이 없습니다. id = " + id));

        return ItemResponseDto.from(item);
    }

    public List<ItemResponseDto> findAllItem() {
        return itemRepository.findAll().stream()
                .map(ItemResponseDto::from)
                .collect(Collectors.toList());
    }

    public Item updateItemById(Long id, ItemUpdateRequestDto requestDto) {
        Item item = itemRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당하는 물품이 없습니다. id " + id));

        item.update(requestDto.getName(), requestDto.getPrice());

        return  itemRepository.save(item);
    }

    public void  deleteItemById(Long id) {
        itemRepository.deleteById(id);
    }
}
