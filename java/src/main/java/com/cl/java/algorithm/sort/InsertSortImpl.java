package com.cl.java.algorithm.sort;

public class InsertSortImpl extends AbstractSort{

	public InsertSortImpl(Comparable[] a) {
		super(a);
	}
	
	@Override
	public void sort() {
		sort0();
		//sort1();		
	}
	/**
	 * For 1000 random Doubles 100 cycle
   		InsertSort is 1.3 times faster than ChooseSort 

	 */
	private void sort0(){
		int n = a.length;
		for(int i = 1; i < n; i++ ){
			for(int j = i ; j > 0 && less(a[j], a[j-1]); j--){
				exch(j,j-1);
			}
		}
	}
	/**
	 * For 1000 random Doubles 100 cycle
   		InsertSort is 1.7 times faster than ChooseSort 

	 */
	private void sort1(){
		int n = a.length;
		int index = 0;
		Comparable t = null;
		for(int i = 1; i < n; i++ ){
			index = i;
			t = a[i];
			for(int j = i ; j > 0 && less(t, a[j-1]); j--){
				index = j - 1;	
				a[j] = a[j-1]; 
			}
			a[index] = t;
		}
	}

}
