package com.cl.spring.aop.first.service;



import com.cl.spring.aop.first.dao.ItemDao;
import com.cl.spring.aop.first.dao.OrderDao;

public class StoreServiceImpl implements StoreService {
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

	public boolean login(String id, String pwd) {
		return id.equals(pwd);
	}

	

}
