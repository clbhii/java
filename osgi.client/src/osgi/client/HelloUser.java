package osgi.client;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceReference;

import osgi.Hello;

public class HelloUser implements BundleActivator {



	/*
	 * (non-Javadoc)
	 * @see org.osgi.framework.BundleActivator#start(org.osgi.framework.BundleContext)
	 */
	public void start(BundleContext ctx) throws Exception {
		ServiceReference ref = ctx.getServiceReference(Hello.class.getName());
	    if (ref != null) {
	        Hello hello = null;
	        try {
	            hello = (Hello) ctx.getService(ref);
	            if (hello != null)
	                hello.sayHello();
	            else
	                System.out.println("Service:Hello---object null");
	        } catch (RuntimeException e) {
	            e.printStackTrace();
	        } finally {
	            ctx.ungetService(ref);
	            hello = null;
	        }
	    } else {
	        System.out.println("Service:Hello---not exists");
	    }
	}

	/*
	 * (non-Javadoc)
	 * @see org.osgi.framework.BundleActivator#stop(org.osgi.framework.BundleContext)
	 */
	public void stop(BundleContext bundleContext) throws Exception {
	}

}
