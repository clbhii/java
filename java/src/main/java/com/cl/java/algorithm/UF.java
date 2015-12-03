package com.cl.java.algorithm;

import com.cl.java.algorithm.perfermance.StopWatch;

import edu.princeton.cs.algs4.In;
/**
 * 
 * @author Administrator
 *
 */
public class UF {

	
	public static void main(String[] args) {
		//UF.test();
		QF.test();
	}
}
/**
 * quick-find算法
 * @author Administrator
 *
 */
class QF{
	private int[] id;	
	private int count ;

	public QF(int N){
		id = new int[N];
		for(int i = 0; i < N; i++) {
			id[i] = i;
		}
		count = N;
	}
	
	public void union(int p, int q) {
		int pId = find(p);
		int qId = find(q);
		if(pId == qId) {
			return;
		}
		for(int i = 0, l = id.length; i < l; i++){
			if(id[i] == pId) {
				id[i] =qId;
			}
		}
		count--;
	}
	
	public int find(int p) {
		return id[p];
	}
	
	public boolean connected(int p, int q) {
		return id[p] == id[q];
	}
	
	public int count(){
		return count;
	}
	
	public static void  test(){
		In in = new In("G:/data/algorithm/mediumUF.txt");
		String line = in.readLine();
		StopWatch watch = new StopWatch();
		QF uf = new QF(Integer.valueOf(line));
		while(in.hasNextLine()){
			line = in.readLine();
			String[] data = line.split(" ");
			int p = Integer.valueOf(data[0]);
			int q = Integer.valueOf(data[1]);
			if(uf.connected(p, q)){
				continue;
			}
			uf.union(p, q);
			//System.out.println(p + " " + q);
		}
		System.out.println("count = " + uf.count + ":" + watch.elapsedTime());
	}
}


/**
 * 
 * quick-union算法
 * @author Administrator
 *
 */
class QU {

	private int[] id;
	
	private int count ;

	public QU(int N){
		id = new int[N];
		for(int i = 0; i < N; i++) {
			id[i] = i;
		}
		count = N;
	}
	
	public void union(int p, int q) {
		int pId = find(p);
		int qId = find(q);
		if(pId == qId) {
			return;
		}
		id[pId] = qId;
		count--;
	}
	
	public int find(int p) {
		while(p != id[p]) {
			p = id[p];
		}
		return p;
	}
	
	public boolean connected(int p, int q) {
		return find(p) == find(q);
	}
	
	public int count(){
		return count;
	}
	
	public static void  test(){
		In in = new In("G:/data/algorithm/largeUF.txt");
		String line = in.readLine();
		StopWatch watch = new StopWatch();
		QU uf = new QU(Integer.valueOf(line));
		while(in.hasNextLine()){
			line = in.readLine();
			String[] data = line.split(" ");
			int p = Integer.valueOf(data[0]);
			int q = Integer.valueOf(data[1]);
			if(uf.connected(p, q)){
				continue;
			}
			uf.union(p, q);
			//System.out.println(p + " " + q);
		}
		System.out.println("count = " + uf.count + ":" + watch.elapsedTime());
	}
}


class WeightedQU{
	private int[] id;
	private int[] sz;
	private int count;
	
	public WeightedQU(int n){
		id = new int[n];
		sz = new int[n];
		for(int i = 0; i < n; i++) {
			id[i] = i;
			sz[i] = 1;
		}
		count = n;
	}
	
	public void union(int p, int q) {
		int pId = find(p);
		int qId = find(q);
		if(pId == qId) {
			return;
		}
		int pSz = sz[pId];
		int qSz = sz[qId];
		if(pSz <= qSz){
			id[pId] = qId;
			sz[qId] = qSz + pSz;
		}else {
			id[qId] = pId;
			sz[pId] = pSz + qSz;
		}	
		count--;
	}
	
	public int find(int p) {
		while(p != id[p]) {
			p = id[p];
		}
		return p;
	}
	
	public boolean connected(int p, int q) {
		return find(p) == find(q);
	}
	
	public int count(){
		return count;
	}
	
	public static void  test(){
		In in = new In("G:/data/algorithm/largeUF.txt");
		String line = in.readLine();
		StopWatch watch = new StopWatch();
		WeightedQU uf = new WeightedQU(Integer.valueOf(line));
		while(in.hasNextLine()){
			line = in.readLine();
			String[] data = line.split(" ");
			int p = Integer.valueOf(data[0]);
			int q = Integer.valueOf(data[1]);
			if(uf.connected(p, q)){
				continue;
			}
			uf.union(p, q);
			//System.out.println(p + " " + q);
		}
		System.out.println("count = " + uf.count + ":" + watch.elapsedTime());
	}
	
}
class MyFastUF {
	private Node[] id;
	private int count;
	
	private class Node {
		private int v;
		private Node next;
		
		private Node(int v, Node next) {
			super();
			this.v = v;
			this.next = next;
		}
	}

	public void union(int p, int q) {
		Node pId = find(p);
		Node qId = find(q);
		if(pId == qId) {
			return;
		}
		Node qLastId = qId;
		while(qLastId.next != null) {			
			qLastId = qLastId.next;
		}
		qLastId.next = pId;
		
		while(pId != null){
			id[pId.v] = qId;
			pId = pId.next;
		}
		count--;
	}
	
	public MyFastUF(int n) {
		id = new Node[n];
		for(int i = 0; i < n; i++) {
			id[i] = new Node(i, null);
		}
		count = n;
	}
	
	public Node find(int p) {
		return id[p];
	}
	
	public boolean connected(int p, int q) {
		return id[p] == id[q];
	}
	
	public int count(){
		return count;
	}
	
}



