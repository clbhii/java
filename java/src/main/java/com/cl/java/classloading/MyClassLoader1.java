package com.cl.java.classloading;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.lang.reflect.Method;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
public class MyClassLoader1 {

	public static void main(String[] args){
		JFrame frame=new ClassLoaderFrame();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
	}
}


 class GBC extends GridBagConstraints 
{
   /**
      Constructs a GBC with a given gridx and gridy position and
      all other grid bag constraint values set to the default.
      @param gridx the gridx position
      @param gridy the gridy position
   */
   public GBC(int gridx, int gridy)
   {
      this.gridx = gridx;
      this.gridy = gridy;
   }
 
   /**
      Sets the cell spans.
      @param gridwidth the cell span in x-direction
      @param gridheight the cell span in y-direction
      @return this object for further modification
   */
   public GBC setSpan(int gridwidth, int gridheight) 
   { 
      this.gridwidth = gridwidth; 
      this.gridheight = gridheight; 
      return this;
   }
 
   /**
      Sets the anchor.
      @param anchor the anchor value
      @return this object for further modification
   */
   public GBC setAnchor(int anchor) 
   { 
      this.anchor = anchor; 
      return this;
   }
   
   /**
      Sets the fill direction.
      @param fill the fill direction
      @return this object for further modification
   */
   public GBC setFill(int fill) 
   { 
      this.fill = fill; 
      return this;
   }
 
   /**
      Sets the cell weights.
      @param weightx the cell weight in x-direction
      @param weighty the cell weight in y-direction
      @return this object for further modification
   */
   public GBC setWeight(double weightx, double weighty) 
   { 
      this.weightx = weightx; 
      this.weighty = weighty; 
      return this;
   }
 
   /**
      Sets the insets of this cell.
      @param distance the spacing to use in all directions
      @return this object for further modification
   */
   public GBC setInsets(int distance) 
   { 
      this.insets = new java.awt.Insets(
            distance, distance, distance, distance);
      return this;
   }
 
   /**
      Sets the insets of this cell.
      @param top the spacing to use on top
      @param left the spacing to use to the left
      @param bottom the spacing to use on the bottom
      @param right the spacing to use to the right
      @return this object for further modification
   */
   public GBC setInsets(int top, int left, int bottom, int right) 
   { 
      this.insets = new java.awt.Insets(
         top, left, bottom, right);
      return this;
   }
 
   /**
      Sets the internal padding
      @param ipadx the internal padding in x-direction
      @param ipady the internal padding in y-direction
      @return this object for further modification
   */
   public GBC setIpad(int ipadx, int ipady) 
   { 
      this.ipadx = ipadx; 
      this.ipady = ipady; 
      return this;
   }
}
class ClassLoaderFrame extends JFrame{
	private JTextField keyField =new JTextField("3",4);
	private JTextField nameField=new JTextField(30);
	private static final int DEFAULT_WIDTH=300;
	private static final int DEFAULT_HEIGHT=200;
	
	public ClassLoaderFrame(){
		setTitle("ClassLoaderTest");
		setSize(DEFAULT_WIDTH,DEFAULT_HEIGHT);
		setLayout(new GridBagLayout());
		add(new JLabel("Class"),new GBC(0,0).setAnchor(GBC.EAST));
		add(nameField,new GBC(1,0).setWeight(100,0).setAnchor(GBC.WEST));
		add(new JLabel("key"),new GBC(0,1).setAnchor(GBC.EAST));
		add(keyField,new GBC(1,1).setWeight(100, 0).setAnchor(GBC.WEST));
		JButton loadButton=new JButton("load");
		add(loadButton,new GBC(2,0).setWeight(100, 0).setAnchor(GBC.WEST));
		loadButton.addActionListener(new ActionListener(){

			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				runClass(nameField.getText(),keyField.getText());
			}
			
		});
		pack();
	}
	
	
	public void runClass(String name,String key){
		ClassLoader loader=new CryptoClassLoader(Integer.parseInt(key));
		Class c;
		try {
			c = loader.loadClass(name);
			String[] args=new String[]{};
			Method m=c.getMethod("main", args.getClass());
			m.invoke(null, (Object)args);
		} catch (Throwable e) {
			// TODO Auto-generated catch block
			JOptionPane.showMessageDialog(this, e);
		}
		
	}
}


class CryptoClassLoader extends ClassLoader{
	private int key;
	
	public CryptoClassLoader(int key){
		this.key=key;
	}
	
	protected Class findClass(String name) throws ClassNotFoundException{
		byte[] classBytes=null;
		
		try {
			classBytes=loadClassBytes(name);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		Class cl=defineClass(name,classBytes,0,classBytes.length);
		if(cl==null){
			throw new ClassNotFoundException(name);
		}
		return cl;
	}
	
	private byte[] loadClassBytes(String name) throws IOException{
		
		String  cname=System.getProperty("user.dir")+"\\bin\\"+name.replace('.', File.separatorChar)+".caesar";
		FileInputStream in =null;
		
		in=new FileInputStream(cname);
	
			
		try{
			
			ByteArrayOutputStream buffer=new ByteArrayOutputStream();
			int ch;
			while((ch=in.read())!=-1){
				byte b=(byte)(ch-key);
				buffer.write(b);
			}
			return buffer.toByteArray();
			
		}finally{
			in.close();
		}
		
		
		
		
	}
}



