package org.likelion.firstspringproject.dto;

import org.likelion.firstspringproject.domain.Item;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor
public class ItemResponseDto {

	private Long id;
	private Long itemId;
	private String name;
	private long price;

	public static ItemResponseDto from(Item item){
		return new ItemResponseDto(item.getId(), item.getItemId(), item.getName(), item.getPrice());
	}
}
