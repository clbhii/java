package com.cl.seckill.service.impl;

import java.util.HashMap;
import java.util.Map;

import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.ZooKeeper;
import org.apache.zookeeper.lock.WriteLock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cl.seckill.dao.ClOrderMapper;
import com.cl.seckill.dao.ClProductMapper;
import com.cl.seckill.entity.ClOrder;
import com.cl.seckill.entity.ClProduct;
import com.cl.seckill.service.ClOrderService;
import com.cl.seckill.util.SysLogUtil;
@Service
public class ClOrderServiceImpl implements ClOrderService{
	
	@Autowired
	private ClOrderMapper clOrderMapper;
	@Autowired
	private ClProductMapper clProductMapper;
	
	
	@Override
	@Transactional(rollbackFor=Exception.class)
	public void order(Integer productId) {
//		order1(productId);
//		order2(productId);
//		order3(productId);
		order4(productId); //有用，数据库锁实现
	}	
	/**
	 * 高并发下，查询订单的数量一样导致多次插入，
	 */
	private void order1(Integer productId) {
		ClProduct clProduct = clProductMapper.selectByPrimaryKey(productId);
		Map<String, Object> queryMap = new HashMap<String, Object>();
		queryMap.put("productId", productId);
		int count = clOrderMapper.countByCriteria(queryMap);
		if(count < clProduct.getNum()){
			ClOrder clOrder = new ClOrder();
			clOrder.setProductId(productId);
			clOrder.setStatus(1);
			clOrderMapper.insert(clOrder);
		}else {
			throw new RuntimeException("没有商品了");
		}
	}
	
	/**
	 * 高并发下，查询订单的数量一样导致多次插入，
	 * 插入后查询数量是否超过，超过就回滚,但是在同一个事物中，查询前，插入，查询后，只能+1,不知道真实的数量
	 */
	@Transactional(rollbackFor=Exception.class)
	private void order2(Integer productId) {
		ClProduct clProduct = clProductMapper.selectByPrimaryKey(productId);
		Map<String, Object> queryMap = new HashMap<String, Object>();
		queryMap.put("productId", productId);
		int count = clOrderMapper.countByCriteria(queryMap);
		if(count < clProduct.getNum()){
			ClOrder clOrder = new ClOrder();
			clOrder.setProductId(productId);
			clOrder.setStatus(1);
			clOrderMapper.insert(clOrder);
			count = clOrderMapper.countByCriteria(queryMap);
			if(count > clProduct.getNum()){
				throw new RuntimeException("超过商品了");
			}
			
		}else {
			throw new RuntimeException("没有商品了");
		}
	}
	
	/**
	 * 1.高并发下，查询订单的数量一样导致多次插入，
	 * 2.插入后查询数量是否超过，超过删除,第二次查询在删除之前，,会导致多删
	 */
	private void order3(Integer productId) {
		ClProduct clProduct = clProductMapper.selectByPrimaryKey(productId);
		Map<String, Object> queryMap = new HashMap<String, Object>();
		queryMap.put("productId", productId);
		int count = clOrderMapper.countByCriteria(queryMap);
		if(count < clProduct.getNum()){
			ClOrder clOrder = new ClOrder();
			clOrder.setProductId(productId);
			clOrder.setStatus(1);
			clOrderMapper.insert(clOrder);
			count = clOrderMapper.countByCriteria(queryMap);
			if(count > clProduct.getNum()){
				clOrderMapper.deleteByPrimaryKey(clOrder.getId());
				throw new RuntimeException("超过商品了");
			}
			
		}else {
			throw new RuntimeException("没有商品了");
		}
	}
	
	/**
	 * 1.高并发下，查询订单的数量一样导致多次插入，
	 * 2.for update 查询时，加锁，，也就是同一时间，只能有一个事物运行，
	 * 注意：for update 锁是加载数据库中，分布式环境也有用
	 * synchronized 只在单机环境下有用,需要实现分布式锁
	 */
	@Transactional(rollbackFor=Exception.class)
	private void order4(Integer productId) {
		ClProduct clProduct = clProductMapper.selectByPrimaryKey(productId);
		Map<String, Object> queryMap = new HashMap<String, Object>();
		queryMap.put("productId", productId);
		
		int count = clOrderMapper.countFroUpdateByCriteria(queryMap);
		SysLogUtil.getSysLog().error(count+"");
		if(count < clProduct.getNum()){
			ClOrder clOrder = new ClOrder();
			clOrder.setProductId(productId);
			clOrder.setStatus(1);
			clOrderMapper.insert(clOrder);
			
		}else {
			throw new RuntimeException("没有商品了");
		}
	}
	
	/**
	 * 1.高并发下，查询订单的数量一样导致多次插入，
	 * 2.需要实现分布式锁
	 */
	@Transactional(rollbackFor=Exception.class)
	private void order5(Integer productId) throws Exception {
		ClProduct clProduct = clProductMapper.selectByPrimaryKey(productId);
		Map<String, Object> queryMap = new HashMap<String, Object>();
		queryMap.put("productId", productId);
		// 创建一个与服务器的连接
		ZooKeeper zk = new ZooKeeper("localhost:2181", 1000, new Watcher() {
			// 监控所有被触发的事件
			public void process(WatchedEvent event) {
				System.out.println("已经触发了" + event.getType() + "事件！" +event.getState());
			}
		});
		WriteLock lock = new WriteLock(zk, "/lock", null);
		while(!lock.lock()){
			Thread.sleep(1000);
		}
		try{
			int count = clOrderMapper.countByCriteria(queryMap);
			SysLogUtil.getSysLog().error(count+"");
			if(count < clProduct.getNum()){
				ClOrder clOrder = new ClOrder();
				clOrder.setProductId(productId);
				clOrder.setStatus(1);
				clOrderMapper.insert(clOrder);
				
			}else {
				throw new RuntimeException("没有商品了");
			}
		}finally {
			lock.unlock();
		}
		
	}
	
}
