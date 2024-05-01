package org.likelion.firstspringproject.domain;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.stereotype.Repository;

@Repository
public class ItemRepository {

	private static final Map<Long, Item> database = new HashMap<>();

	public Item save(Item item){
		database.put(item.getId(), item);
		return database.get(item.getId());
	}

	public Optional<Item> findById(Long id){
		return Optional.ofNullable(database.get(id));
	}

	public List<Item> findAll(){
		return database.values().stream().toList();
	}

	public void deleteById(Long id){
		database.remove(id);
	}

	public void clear(){
		database.clear();
	}

}
