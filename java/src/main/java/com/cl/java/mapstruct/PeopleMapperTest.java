package com.cl.java.mapstruct;

import org.junit.Test;

public class PeopleMapperTest {
	@Test
	public void test1() {
		PeopleDO peopleDO = new PeopleDO();
		peopleDO.setAge(1);
		peopleDO.setName("ddd");
		PeopleDTO peopleDTO = new PeopleDTO();
		PeopleMapper.MAPPER.mapping(peopleDO, peopleDTO);
		System.out.println(peopleDTO);
	}
}
