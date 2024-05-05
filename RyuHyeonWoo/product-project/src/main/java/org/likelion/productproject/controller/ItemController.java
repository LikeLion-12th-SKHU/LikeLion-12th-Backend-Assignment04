package org.likelion.productproject.controller;

import lombok.RequiredArgsConstructor;
import org.likelion.productproject.domain.ItemRepository;
import org.likelion.productproject.dto.ItemResponseDto;
import org.likelion.productproject.dto.ItemSaveRequestDto;
import org.likelion.productproject.dto.ItemUpdateRequestDto;
import org.likelion.productproject.service.ItemService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
public class ItemController {

    private final ItemService itemService;

    @PostMapping("Item")
    public void save(@RequestBody ItemSaveRequestDto requestDto) {
        itemService.saveItem(requestDto);
    }

    @GetMapping("item/{id}")
    public ItemResponseDto findItemById(@PathVariable Long id) {
        return itemService.findItemById(id);
    }

    @GetMapping("Item")
    public List<ItemResponseDto> findAllItem() {
        return itemService.findAllItem();
    }

    @PatchMapping("item/{id}")
    public void updateItemById(@PathVariable Long id, @RequestBody ItemUpdateRequestDto requestDto) {
        itemService.updateItemById(id, requestDto);
    }

    @DeleteMapping("item/{id}")
    public void deleteItemById(@PathVariable Long id) {
        itemService.deleteItemById(id);
    }
}
