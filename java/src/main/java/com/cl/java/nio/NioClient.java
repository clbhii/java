package com.cl.java.nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;


public class NioClient {

    private Selector selector = null;
    static final int port = 9999;
    private Charset charset = Charset.forName("UTF-8");
    private SocketChannel sc = null;
    private String name = "";
    private static String USER_EXIST = "system message: user exist, please change a name";
    private static String USER_CONTENT_SPILIT = "#@#";
    
    public NioClient(){
    	try {
			init();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
    public void init() throws IOException
    {
    	 selector = Selector.open();
         //连接远程主机的IP和端口
         sc = SocketChannel.open(new InetSocketAddress("127.0.0.1",port));
    }
  
    
    
    public static void main(String[] args) throws IOException
    {
    	List<NioClient> list = new ArrayList<NioClient>();
    	for(int i=0;i<10000;i++){
    		list.add(new NioClient());
    	}
    	while(true){
    		 try {
    			 System.out.println(list.size());
 				Thread.sleep(10000);
 			} catch (InterruptedException e) {
 				// TODO Auto-generated catch block
 				e.printStackTrace();
 			}
    	}
       
    }
}