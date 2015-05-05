package osgi;


public class HelloImpl implements Hello {

	final String helloString;
	
	
	public HelloImpl(String helloString) {
		this.helloString = helloString;
	}


	public void sayHello() {
		System.out.println(this.helloString);
	}

}
