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
		peopleDTO = PeopleMapper.MAPPER.mapping(peopleDO);
		System.out.println(peopleDTO);
		PeopleDO peopleDO1 = new PeopleDO();
		peopleDO.setAge(2);
		peopleDO.setName("ddd2");
		peopleDTO = PeopleMapper.MAPPER.mapping(peopleDO,peopleDO1);
		System.out.println(peopleDTO);
	}
}
