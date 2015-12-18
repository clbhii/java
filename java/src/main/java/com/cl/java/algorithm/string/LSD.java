package com.cl.java.algorithm.string;

public class LSD {

	public static void  sort(String[] a, int w) {
		int n = a.length;
		int R = 256;
		String[] aux = new String[n];
		for(int d = w - 1; d >= 0; d--) {
			int[] count = new int[R + 1];
			for(int i = 0; i < n; i++) {
				count[a[i].charAt(d) + 1]++;
			}
			for(int i = 0; i < R; i++) {
				count[i + 1] += count[i];
			}
			for(int i = 0; i < n; i++) {
				aux[count[a[i].charAt(d)]++] = a[i];
			}
			for(int i = 0; i < n; i++) {
				a[i] = aux[i];
			}
			System.out.println("d=" + d);
			print(a);
		}
	}
	
	private static void print(String[] a) {
		for(String str : a) {
			System.out.println(str);
		}
	}
	public static void main(String[] args) {
		String[] a = {"4PGC938", "2IYE230", "3CIO720", "1ICK750"};
		sort(a, 7);
	}
}
