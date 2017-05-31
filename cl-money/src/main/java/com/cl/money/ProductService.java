package com.cl.money;

import java.util.Map;

public interface ProductService {
	public  void handle(int page, Map<String, Double> map);
	
	public void execute() ;
	
	public void remind();
	
	public void remind(int page);
}
