package osgi;

import java.util.ArrayList;
import java.util.List;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceRegistration;


public class HelloActivator implements BundleActivator {

	private List<ServiceRegistration> registrations = new ArrayList<ServiceRegistration>();
	@Override
	public void start(BundleContext ctx) throws Exception {
		// TODO Auto-generated method stub
		 registrations.add(ctx.registerService(Hello.class.getName(),new HelloImpl("Hello, OSGi"), null));
	}

	@Override
	public void stop(BundleContext arg0) throws Exception {
		// TODO Auto-generated method stub
		for (ServiceRegistration registration : registrations) {
	        System.out.println("unregistering: "+ registration);
	        registration.unregister();
	    }
	}

}
