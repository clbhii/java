package com.cl.money;

import java.util.Comparator;
import java.util.Map;
import java.util.TreeMap;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.config.ClientConfig;
import com.sun.jersey.api.client.config.DefaultClientConfig;

public abstract class AbstractProduct {
	static ClientConfig config = new DefaultClientConfig();
	static Client client = Client.create(config);
	static Map<String, Double> map = new TreeMap<String, Double>(new Comparator<String>(){  
        /* 
         * int compare(Object o1, Object o2) 返回一个基本类型的整型， 
         * 返回负数表示：o1 小于o2， 
         * 返回0 表示：o1和o2相等， 
         * 返回正数表示：o1大于o2。 
         */  
        public int compare(String o1, String o2) {  
            //指定排序器按照降序排列  
            return Integer.valueOf(o1)-Integer.valueOf(o2);  
        }     
    });
	static java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat("yyyyMM");
	
	public abstract void handle();
	
	public void execute(){
		long begin = System.currentTimeMillis();
		handle();
		print();
		System.out.println("耗时：" + (System.currentTimeMillis() - begin));
	}
	public void print(){
		for (String key : map.keySet()) {
			System.out.println(key + ":" + map.get(key));
		}
	}
}
