package com.cl.java.algorithm.search;


import java.io.File;

import com.cl.java.algorithm.perfermance.StopWatch;

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdOut;

public class FrequencyCounter {

	public static void main(String[] args) {
		
        //minlen = 10;
        //ST<String, Integer> st = new ST<String, Integer>();
//        SequentialSearchST<String, Integer> st1 = new SequentialSearchST<String, Integer>();
//        test("G:/data/algorithm/tale.txt", st1);
//        BinarySearchST<String, Integer> st2 = new BinarySearchST<String, Integer>(100000);
//        test("G:/data/algorithm/tale.txt", st2);
//		  RedBlackBST<String, Integer> st3 = new RedBlackBST<String, Integer>();
//        test("G:/data/algorithm/tale.txt", st3);
//        StdOut.println("cmpCount = " + st3.putCmpCount);
        SeparateChainingHashSet<String, Integer> st4 = new SeparateChainingHashSet<String, Integer>(100000);
        test("G:/data/algorithm/leipzig1M.txt", st4);
    }
	
	public static void test(String fileName,SearchST<String, Integer> st){
		StopWatch watch = new StopWatch();
        int distinct = 0, words = 0;
        int minlen = 1;
        minlen = 8;
		In in = new In(new File(fileName));
        // compute frequency counts
        while (!in.isEmpty()) {
            String key = in.readString();
            if (key.length() < minlen) continue;
            words++;
            if (st.contains(key)) {
                st.put(key, st.get(key) + 1);
            }
            else {
                st.put(key, 1);
                distinct++;
            }
        }

        // find a key with the highest frequency count
//        String max = "";
//        st.put(max, 0);
//        for (String word : st) {
//            if (st.get(word) > st.get(max))
//                max = word;
//        }
//
//        StdOut.println(max + " " + st.get(max));
        StdOut.println("distinct = " + distinct);
        StdOut.println("words    = " + words);
        StdOut.println("cost = " + watch.elapsedTime());
	}
}
