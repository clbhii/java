package com.cl.java.algorithm.string;

public class Quick3String {

	 private static final int CUTOFF =  15;   // cutoff to insertion sort
	
	public static void sort(String[] a) {
		sort(a, 0, a.length - 1, 0);
	}
	
	private static void sort(String[] a, int lo, int hi, int d) {
	    if (hi <= lo + CUTOFF) {
            insertion(a, lo, hi, d);
            return;
        }
		
//		if(hi <= lo) {
//			return;
//		}
		int lt = lo;
		int gt = hi;
		int i = lo + 1;
		int v = charAt(a[lo], d);
		while(i <= gt) {
			int cmp = charAt(a[i], d) - v;
			if(cmp < 0) {
				exch(a, i++, lt++);
			}else if (cmp > 0) {
				exch(a, i, gt--);
			}else {
				i++;
			}
		}
		sort(a, lo, lt - 1, d);
		if(v >= 0) {
			sort(a, lt, gt, d + 1);
		}
		sort(a, gt + 1, hi, d);
	}
	private static int charAt(String a, int d) {
		if(d >= a.length()){
			return -1;
		}
		return a.charAt(d);
	}
	
	
	private static void exch(String[] a, int i, int j) {
		String tmp = a[i];
		a[i] = a[j];
		a[j] = tmp;
	}
	
	 // sort from a[lo] to a[hi], starting at the dth character
    private static void insertion(String[] a, int lo, int hi, int d) {
        for (int i = lo; i <= hi; i++)
            for (int j = i; j > lo && less(a[j], a[j-1], d); j--)
                exch(a, j, j-1);
    }

    private static boolean less(String v, String w, int d) {
        for (int i = d; i < Math.min(v.length(), w.length()); i++) {
            if (v.charAt(i) < w.charAt(i)) return true;
            if (v.charAt(i) > w.charAt(i)) return false;
        }
        return v.length() < w.length();
    }
    
	
}
