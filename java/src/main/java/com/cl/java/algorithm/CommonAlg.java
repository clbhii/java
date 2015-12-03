package com.cl.java.algorithm;

public class CommonAlg {

	//获取p,q的最大公约数	（欧几里德算法）
	//如果q为0,最大公约数为p,否则，将p除q得到r,p和q的最大公约数就是q和r的最大公约数
	public static int gcd(int p, int q) {
		if(q == 0) {
			return p;
		}
		int r = p%q;	
		return gcd (q, r);
	}
	
	public static void main(String[] args) {
		System.out.println(gcd(6,8));
	}
}
