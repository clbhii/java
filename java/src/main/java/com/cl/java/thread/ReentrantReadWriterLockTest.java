package com.cl.java.thread;

import java.util.concurrent.locks.ReentrantReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock.ReadLock;
import java.util.concurrent.locks.ReentrantReadWriteLock.WriteLock;

public class ReentrantReadWriterLockTest {
	ReentrantReadWriteLock lock = new ReentrantReadWriteLock();
	ReadLock readLock = lock.readLock();
	WriteLock writeLock = lock.writeLock();
	int count = 0;
	public void inc(){
		writeLock.lock();
		try{
			count ++;
		}finally {
			writeLock.unlock();
		}
	}
	
	public void get(){
		readLock.lock();
		try{
			System.out.println(count);
		}finally {
			readLock.unlock();
		}
	}
	
	public static void main(String[] args) {
		final ReentrantReadWriterLockTest test = new ReentrantReadWriterLockTest();
		new Thread(new Runnable(){

			public void run() {
				test.get();				
			}
			
		}).start();
		new Thread(new Runnable(){

			public void run() {
				test.get();				
			}
			
		}).start();
		new Thread(new Runnable(){

			public void run() {
				test.inc();				
			}
			
		}).start();
	}
}
