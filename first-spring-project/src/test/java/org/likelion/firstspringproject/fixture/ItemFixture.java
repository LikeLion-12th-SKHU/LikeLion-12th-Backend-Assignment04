package org.likelion.firstspringproject.fixture;

import org.likelion.firstspringproject.domain.Item;

public class ItemFixture {

	public static final Item ITEM_1 = new Item(
		1L,
		201914089L,
		"동재시치의 조기퇴근 강탈 3회 이용권",
		10000L
	);

	public static final Item ITEM_2 = new Item(
		2L,
		202012345L,
		"준영시치의 술강요 3회 방어권",
		15000L
	);


	public static final Item ITEM_3 = new Item(
		3L,
		202100000L,
		"영빈시치, 시은시치 회식 참여 3회권",
		3000L
	);
}
