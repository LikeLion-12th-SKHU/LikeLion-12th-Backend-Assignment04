package org.likelion.firstspringproject.controller;

import java.util.List;

import org.likelion.firstspringproject.domain.Item;
import org.likelion.firstspringproject.dto.ItemResponseDto;
import org.likelion.firstspringproject.dto.ItemSaveRequestDto;
import org.likelion.firstspringproject.dto.ItemUpdateRequestDto;
import org.likelion.firstspringproject.service.ItemService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class ItemController {

	private final ItemService itemService;

	@PostMapping("/items")
	public ResponseEntity<ItemResponseDto> save(@RequestBody ItemSaveRequestDto requestDto){
		return new ResponseEntity<>(itemService.saveItem(requestDto), HttpStatus.CREATED);
	}

	@GetMapping("/items/{id}")
	public ResponseEntity<ItemResponseDto> findItemById(@PathVariable Long id){
		return new ResponseEntity<>(itemService.findItemById(id), HttpStatus.OK);
	}

	@GetMapping("/items")
	public ResponseEntity<List<ItemResponseDto>> findAllItems(){
		return new ResponseEntity<>(itemService.findAllItems(), HttpStatus.OK);
	}

	@PatchMapping("/items/{id}")
	public ResponseEntity<ItemResponseDto> updateItemById(@PathVariable Long id, @RequestBody ItemUpdateRequestDto requestDto){
		return new ResponseEntity<>(itemService.updateItemById(id, requestDto), HttpStatus.OK);
	}

	@DeleteMapping("/items/{id}")
	public ResponseEntity<Void> deleteItemById(@PathVariable Long id){
		itemService.deleteItemById(id);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}


}
