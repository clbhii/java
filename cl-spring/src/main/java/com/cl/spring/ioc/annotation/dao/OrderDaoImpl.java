package com.cl.spring.ioc.annotation.dao;

import org.springframework.stereotype.Repository;

@Repository("orderDao")
public class OrderDaoImpl implements OrderDao {

	public void save() {
		System.out.println("orderDdao.save()");
	}

}
