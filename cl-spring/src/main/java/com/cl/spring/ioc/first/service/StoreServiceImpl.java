package com.cl.spring.ioc.first.service;

import java.util.Set;

import com.cl.spring.ioc.first.dao.ItemDao;
import com.cl.spring.ioc.first.dao.OrderDao;

public class StoreServiceImpl implements StoreService {

	private int sn;
	private Set someSet;
	private OrderDao orderDao;
	private ItemDao itemDao;
	
	
	
	
	public StoreServiceImpl() {
		super();
	}

	public StoreServiceImpl(OrderDao orderDao, ItemDao itemDao) {
		super();
		this.orderDao = orderDao;
		this.itemDao = itemDao;
	}

	public void check(){
		if(orderDao==null||itemDao==null){
			throw new RuntimeException();
		}
	}
	
	public void submitOrder() {
		orderDao.save();
		itemDao.update();
	}

	public ItemDao getItemDao() {
		return itemDao;
	}

	public void setItemDao(ItemDao itemDao) {
		this.itemDao = itemDao;
	}

	public OrderDao getOrderDao() {
		return orderDao;
	}

	public void setOrderDao(OrderDao orderDao) {
		this.orderDao = orderDao;
	}

	public int getSn() {
		return sn;
	}

	public void setSn(int sn) {
		this.sn = sn;
	}

	public Set getSomeSet() {
		return someSet;
	}

	public void setSomeSet(Set someSet) {
		this.someSet = someSet;
	}

}
