package com.cl.spring.aop.annotation.service;

import java.util.Set;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.cl.spring.aop.annotation.dao.ItemDao;
import com.cl.spring.aop.annotation.dao.OrderDao;



@Service("storeService")
public class StoreServiceImpl implements StoreService {

	
	private int sn;
	private Set someSet;
	@Resource(name="orderDao")
	private OrderDao orderDao;
	@Resource//autowire  byType
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
	
	@Transactional(propagation=Propagation.REQUIRED)
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
