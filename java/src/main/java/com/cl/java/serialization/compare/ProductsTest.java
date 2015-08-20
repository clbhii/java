package com.cl.java.serialization.compare;

import java.util.ArrayList;
import java.util.List;

import com.cl.java.serialization.compare.Products2.Products22;
import com.cl.java.serialization.compare.Products2.Products22.Builder;
import com.dyuproject.protostuff.LinkedBuffer;
import com.dyuproject.protostuff.ProtostuffIOUtil;
import com.dyuproject.protostuff.Schema;
import com.dyuproject.protostuff.runtime.RuntimeSchema;
import com.google.protobuf.InvalidProtocolBufferException;
import com.thoughtworks.xstream.XStream;

public class ProductsTest {
	private long usedTime;

	public  List<String> serializeXMLProductsList(List<Products> pList) {
        if(pList == null) {
            System.out.println("【XmlSerializeServiceImpl-serializeProductsListService】pList参数为空");
            return null;
        }
        long start = System.currentTimeMillis() ;
        XStream x = new XStream();
        x.alias("Products", Products.class);
        List<String> strList = new ArrayList<String>();
        for(Products p : pList) {
            String str = x.toXML(p);
            strList.add(str);
        }
        long end = System.currentTimeMillis() ;
        
        usedTime = end - start ;
        System.out.println(usedTime);
        return strList;
    }
	
	public List<Products> deserializeXMLDataListToProductsList(
            List<String> xmlStrList) {
        long start = System.currentTimeMillis();
        List<Products> productsList = new ArrayList<Products>();
        XStream xs = new XStream();
        xs.alias("Products", Products.class);
        for(String xmlStr : xmlStrList) {
            Products p = (Products)xs.fromXML(xmlStr);
            productsList.add(p);
        }
        long end = System.currentTimeMillis();
        usedTime = end - start ; 
        System.out.println(usedTime);
        return productsList;
    }
	
	public static void main(String[] args) {
		ProductsTest test = new ProductsTest();
		List<Products> pList = new ArrayList<Products>();
		for(int i = 0; i < 25000; i++){
			pList.add(new Products());
		}
		test.deserializeXMLDataListToProductsList(test.serializeXMLProductsList(pList));
		
//		List<Builder> bList = new ArrayList<Builder>();
//		for(int i = 0; i < 10000; i++){
//			bList.add(Products2.Products22.newBuilder());
//		}
//		test.deserializeProtoBufDataListToProducts22List(test.serializeProtoBufProductsList(bList));
		
		test.deserializeProtoStuffDataListToProductsList(test.serializeProtoStuffProductsList(pList));
	}
	
	public List<byte[]> serializeProtoBufProductsList(
            List<Builder> builderList) {
        if(builderList == null) {
            System.out.println("【ProtoBufSerializeServiceImpl-serializeProtoBufProductsService】builderList==null");
        }
        long start = System.currentTimeMillis();
        List<byte[]> bytesList = new ArrayList<byte[]>();
        for(Products2.Products22.Builder p22Builder : builderList){
            Products2.Products22 p22 = p22Builder.build();
            byte[] bytes = p22.toByteArray();
            bytesList.add(bytes);
        }
        long end = System.currentTimeMillis();
        usedTime = end - start ;
        System.out.println(usedTime);
        return bytesList;
    }
	
	public List<Products22> deserializeProtoBufDataListToProducts22List(
            List<byte[]> bytesList) {
        long start = System.currentTimeMillis();
        List<Products22> list = new ArrayList<Products22>();
        for(byte[] b : bytesList) {
            try {
                list.add(Products2.Products22.parseFrom(b));
            } catch (InvalidProtocolBufferException e) {
                e.printStackTrace();
            }
        }
        long end = System.currentTimeMillis();
        usedTime = end - start;
        System.out.println(usedTime);
        return list;
    }
	
	public List<byte[]> serializeProtoStuffProductsList(List<Products> pList) {
        if(pList == null  ||  pList.size() <= 0) {
            return null;
        }
        long start = System.currentTimeMillis() ;
        List<byte[]> bytes = new ArrayList<byte[]>();
        Schema<Products> schema = RuntimeSchema.getSchema(Products.class);
        LinkedBuffer buffer = LinkedBuffer.allocate(4096);
        byte[] protostuff = null;
        for(Products p : pList) {
            try {
                protostuff = ProtostuffIOUtil.toByteArray(p, schema, buffer);
                bytes.add(protostuff);
            } finally {
                buffer.clear();
            }
        }
        long end = System.currentTimeMillis() ;
        this.usedTime = end - start;
        System.out.println(usedTime);
        return bytes;
    }
	
	public List<Products> deserializeProtoStuffDataListToProductsList(
            List<byte[]> bytesList) {
        if(bytesList == null || bytesList.size() <= 0) {
            return null;
        }
        long start = System.currentTimeMillis() ;
        Schema<Products> schema = RuntimeSchema.getSchema(Products.class);
        List<Products> list = new ArrayList<Products>();
        for(byte[] bs : bytesList) {
            Products product = new Products();
            ProtostuffIOUtil.mergeFrom(bs, product, schema);
            list.add(product);
        }
        long end = System.currentTimeMillis() ;
        System.out.println(usedTime);
        this.usedTime = end - start;
        return list;
    }
}
