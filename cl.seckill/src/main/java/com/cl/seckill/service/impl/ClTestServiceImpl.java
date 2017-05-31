package com.cl.seckill.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cl.seckill.dao.ClTestMapper;
import com.cl.seckill.entity.ClTest;
import com.cl.seckill.service.ClTestService;

@Service
public class ClTestServiceImpl implements ClTestService{
	
	@Autowired
	private ClTestMapper clTestMapper;
	
	@Override
	@Transactional(rollbackFor=Exception.class)
	public void insertList(List<ClTest> list) {
		for(ClTest test : list) {
			clTestMapper.insert(test);
			//System.out.println(Thread.currentThread() + ":" + test.getId());
			System.out.println(Thread.currentThread() + ":" + clTestMapper.getLastId());
			
		}
		
	}

	
	
}
