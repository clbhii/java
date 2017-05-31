package com.cl.money;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.TreeMap;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.config.ClientConfig;
import com.sun.jersey.api.client.config.DefaultClientConfig;

public abstract class AbstractProduct implements ProductService{
	static ClientConfig config = new DefaultClientConfig();
	static Client client = Client.create(config);
	static Map<String, Double> map = createMap();
	static java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat("yyyyMM");
	
	private Integer pagesize = 2;
	
	public AbstractProduct(Integer pagesize) {
		super();
		this.pagesize = pagesize;
	}

	
	
	private static Map<String, Double> createMap(){
		return new TreeMap<String, Double>(new Comparator<String>(){  
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
	}
	
	public void execute() {
		long begin = System.currentTimeMillis();
		List<Integer> pages = new ArrayList<>();
		for (int page = 1; page < pagesize; page++)
			pages.add(page);
		
		System.setProperty("java.util.concurrent.ForkJoinPool.common.parallelism", "16");
		pages.parallelStream().map((Integer page) -> {
			Map<String, Double> map = createMap();
			handle(page, map);
			return map;
		}).reduce((Map<String, Double> map1, Map<String, Double> map2) -> {
			for(Entry<String, Double> entry : map2.entrySet()){
				String key = entry.getKey();
				Double double1 = map1.get(key);
				if(double1 == null) {
					double1 = 0d;
				}
				double1 += entry.getValue();
				map1.put(key, double1);
			}
			return map1;
		}).get().entrySet().forEach(e -> {
			System.out.println(e.getKey() + ":" + e.getValue());
		});
		System.out.println("耗时：" + (System.currentTimeMillis() - begin));
	}
	public void print(){
		for (String key : map.keySet()) {
			System.out.println(key + ":" + map.get(key));
		}
	}
	
	
	
	@Override
	public void handle(int page, Map<String, Double> map) {
		
	}



	@Override
	public void remind(int page) {
		
	}

	public void remind(){
		
		while(true){
			AtomicBoolean flag = new AtomicBoolean(false);
			List<Integer> pages = new ArrayList<>();
			for (int page = 1; page < pagesize; page++)
				pages.add(page);
			
			System.setProperty("java.util.concurrent.ForkJoinPool.common.parallelism", "16");
			pages.parallelStream().forEach((Integer page) -> {
				remind(page);
			});
			try {
				Thread.sleep(5000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
	}
}
