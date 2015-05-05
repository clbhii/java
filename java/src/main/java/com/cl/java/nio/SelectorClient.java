package com.cl.java.nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.channels.SocketChannel;
import java.util.ArrayList;
import java.util.List;

public class SelectorClient {

	
	public static void main(String[] args) throws IOException{
		List<SocketChannel> channleList = new ArrayList<SocketChannel>();
		for(int i = 0; i < 10000; i++)
		channleList.add(SocketChannel.open(new InetSocketAddress("127.0.0.1",5555)));
		
		while(true){
   		 try {
   			 System.out.println(channleList.size());
				Thread.sleep(10000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
   	}
	}
}
