package org.likelion.firstspringproject.service;

import java.util.List;
import java.util.stream.Collectors;

import org.likelion.firstspringproject.domain.Item;
import org.likelion.firstspringproject.domain.ItemRepository;
import org.likelion.firstspringproject.dto.ItemResponseDto;
import org.likelion.firstspringproject.dto.ItemSaveRequestDto;
import org.likelion.firstspringproject.dto.ItemUpdateRequestDto;
import org.likelion.firstspringproject.exception.NotFoundException;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ItemService {

	private final ItemRepository itemRepository;

	public static long sequence = 0L;

	public ItemResponseDto saveItem(ItemSaveRequestDto requestDto){
		Item item = Item.builder()
			.id(++sequence)
			.itemId(requestDto.itemId())
			.name(requestDto.name())
			.price(requestDto.price())
			.build();
		return ItemResponseDto.from(itemRepository.save(item));
	}

	public ItemResponseDto findItemById(Long id){
		Item item = itemRepository.findById(id)
			.orElseThrow(() -> new NotFoundException("해당하는 상품이 없습니다. id = "+ id));

		return ItemResponseDto.from(item);
	}

	public List<ItemResponseDto> findAllItems() {
		return itemRepository.findAll().stream()
			.map(ItemResponseDto::from)
			.collect(Collectors.toList());
	}

	public ItemResponseDto updateItemById(Long id, ItemUpdateRequestDto requestDto){
		Item item = itemRepository.findById(id)
			.orElseThrow(()-> new NotFoundException("해당하는 상품이 없습니다. id="+id));
		item.update(requestDto.name(), requestDto.price());
		return ItemResponseDto.from(itemRepository.save(item));
	}

	public void deleteItemById(Long id){
		itemRepository.deleteById(id);
	}
}
