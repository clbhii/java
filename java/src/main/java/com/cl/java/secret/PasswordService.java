package com.cl.java.secret;

public interface PasswordService {

	public void init();
	
	public String createPassword();
	
	public String createPassword(int place);
}
