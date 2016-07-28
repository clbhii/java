package com.cl.spring.transaction.service.impl;

import javax.annotation.Resource;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import com.cl.spring.transaction.entity.Foo;
import com.cl.spring.transaction.exception.UnsupportedException;

@Component
public class FooServiceImpl {
	@Resource
	private JdbcTemplate jdbcTemplate;
	public Foo getFoo(String FooName) {
		
		throw  new UnsupportedException();
	}

	public void insertFoo(Foo foo) {
		// TODO Auto-generated method stub
		jdbcTemplate.update("insert into TB_AGENT_CONSULTATION(id,accountno) values(TB_AGENT_CONSULTATION_SEQ.nextval,'1')");
		
		
	}

	public void updateFoo(Foo foo) {
		// TODO Auto-generated method stub
		jdbcTemplate.update("update  TB_AGENT_CONSULTATION set accountno='3' where id=29");
		throw  new UnsupportedException();
	}

	public JdbcTemplate getJdbcTemplate() {
		return jdbcTemplate;
	}

	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	
}
