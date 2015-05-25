package com.cl.java.classloading;

public class FileSystemClassLoader extends ClassLoader {

	@Override
	protected Class<?> findClass(String arg0) throws ClassNotFoundException {
		// TODO Auto-generated method stub
		return super.findClass(arg0);
	}
	
	public static void main(String[] args){
		ClassLoader classLoader=FileSystemClassLoader.class.getClassLoader();
		ClassLoader parentClassLoader=null;
		if(classLoader!=null){
			System.out.println(classLoader.toString());
			parentClassLoader=classLoader.getParent();
			System.out.println(parentClassLoader.toString());
			//System.out.println(parentClassLoader.getParent().toString());
		}
	}
	
}
