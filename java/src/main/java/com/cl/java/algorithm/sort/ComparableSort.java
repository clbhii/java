package com.cl.java.algorithm.sort;

import com.cl.java.algorithm.perfermance.StopWatch;

import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;

public class ComparableSort {

	public static double time(String algs, Double[] a) {
		StopWatch watch = new StopWatch();
		if("ChooseSort".equals(algs)){
			new ChooseSortImpl(a).sort();
		}else if("InsertSort".equals(algs)){
			new InsertSortImpl(a).sort();
		}
		else if("ShellSort".equals(algs)){
			new ShellSortImpl(a).sort();
		}
		else if("MergeSort".equals(algs)){
			new MergeSortImpl(a).sort();
		}
		return watch.elapsedTime();
	}
	
	public static double timeRandomInput(String algs, int n, int t){
		Double[] a =new Double[n];
		double total = 0;
		for(int i = 0; i< t; i++) {
			for(int j = 0; j < n; j++){
				a[j] = StdRandom.uniform();						
			}
			total += time(algs, a);
		}
		return total;
	}
	
	public static void main(String[] args) {
		int n = 100000;
		int t = 100;
		
//		String alg1 = "InsertSort";
//		String alg2 = "ChooseSort";
		
//		String alg1 = "ShellSort";
//		String alg2 = "InsertSort";
		
		String alg1 = "MergeSort";
		String alg2 = "ShellSort";
		double t1 = timeRandomInput(alg1, n, t);
		double t2 = timeRandomInput(alg2, n, t);
		StdOut.printf("For %d random Doubles %d cycle \n   %s is %.1f times faster than %s \n",n, t, alg1, t2/t1, alg2);
	}
}
