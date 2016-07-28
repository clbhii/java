package com.cl.spring.extend;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.springframework.beans.factory.FactoryBean;

public class SAXParserFactoryBean implements FactoryBean {

	public Object getObject() throws Exception {
		return SAXParserFactory.newInstance().newSAXParser();
	}

	public Class getObjectType() {
		return SAXParser.class;
	}

	public boolean isSingleton() {
		return false;
	}

}
