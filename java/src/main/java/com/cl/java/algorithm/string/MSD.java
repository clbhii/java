package com.cl.java.algorithm.string;


public class MSD {
	private static int R = 256;
	private static final int M = 12;
	private static String[] aux;
	private static int charAt(String s, int d) {
		if(d < s.length()) {
			return s.charAt(d);
		}
		return -1;
	}
	
	public static void sort(String[] a) {
		int N = a.length;
		aux = new String[N];
		sort(a, 0, N - 1, 0);
	}
	
	private static void sort(String[] a, int lo, int hi, int d) {
//		if(hi <= lo + M) {
//			Insertion.sort(a, lo, hi);
//			return;
//		}
		if(hi <= lo) {
			return;
		}
		int[] count = new int[R +2];
		for(int i = lo; i <= hi; i++) {
			count[charAt(a[i], d) + 2]++;
		}
		for(int r = 0; r < R + 1; r++) {
			count[r + 1] += count[r];
		}
		for(int i = lo; i <= hi; i++) {
			aux[count[charAt(a[i], d) + 1]++] = a[i];
		}
		for(int i = lo; i <= hi; i++) {
			a[i] = aux[i-lo];
		}
		System.out.println("d=" + d);
		print(a);
		for(int r = 0; r < R; r++) {
			sort(a, lo + count[r], lo + count[r + 1] - 1, d + 1);
		}
	}
	
	private static void print(String[] a) {
		for(String str : a) {
			System.out.println(str);
		}
	}
	public static void main(String[] args) {
		String[] a = {"4PGC9", "1IYE30", "4O720", "1I0"};
		sort(a);
	}
}
