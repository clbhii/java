package com.cl.java.nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Set;

public class SelectorServer {

	public static void Server()throws IOException{
		Selector selector = Selector.open();
		ServerSocketChannel channel = ServerSocketChannel.open();
		channel.socket().bind(new InetSocketAddress(5555)); 
		channel.configureBlocking(false);
		channel.register(selector, SelectionKey.OP_ACCEPT);
		while(true) {
		  int readyChannels = selector.select();
		  if(readyChannels == 0) continue;
		  Set selectedKeys = selector.selectedKeys();
		  Iterator keyIterator = selectedKeys.iterator();
		  while(keyIterator.hasNext()) {
		    SelectionKey key = (SelectionKey) keyIterator.next();
		    if(key.isAcceptable()) {
		    	 	SocketChannel sc = channel.accept();
		            //非阻塞模式
		            sc.configureBlocking(false);
		            //注册选择器，并设置为读取模式，收到一个连接请求，然后起一个SocketChannel，并注册到selector上，之后这个连接的数据，就由这个SocketChannel处理
		            sc.register(selector, SelectionKey.OP_READ);
		            
		            //将此对应的channel设置为准备接受其他客户端请求
		            key.interestOps(SelectionKey.OP_ACCEPT);
		            System.out.println("Server is listening from client :" );
		    } else if (key.isConnectable()) {
		        // a connection was established with a remote server.
		    } else if (key.isReadable()) {
		        // a channel is ready for reading
		    } else if (key.isWritable()) {
		        // a channel is ready for writing
		    }
		    keyIterator.remove();
		  }
		}
	}
	
	
	public static void main(String[] args) throws IOException{
		Server();
	}
	
}
