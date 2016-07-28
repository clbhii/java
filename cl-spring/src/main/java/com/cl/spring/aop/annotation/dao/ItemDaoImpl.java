package com.cl.spring.aop.annotation.dao;

import org.springframework.stereotype.Repository;

@Repository("itemDao")
public class ItemDaoImpl implements ItemDao {

	public void update() {
		System.out.println("itemDao.update()");
	}

}
