package com.cl.spring.aop.annotation;

import java.net.URL;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.cl.spring.aop.annotation.service.StoreService;

/**
 * 
 *  @Service用于标注业务层组件
	@Controller用于标注控制层组件（如struts中的action）
	@Repository用于标注数据访问组件，即DAO组件
	@Component泛指组件，当组件不好归类的时候，我们可以使用这个注解进行标注。
	
	getBean的默认名称是类名（头字母小写），如果想自定义，可以@Service(“aaaaa”)这样来指定。
	这种bean默认是“singleton”的，如果想改变，可以使用@Scope(“prototype”)来改变。
	
	当接口存在两个实现类的时候必须使用@Qualifier指定注入哪个实现类，否则可以省略，只写@Autowired。
 * @author cl
 *
 */
public class Test {

	public static void main(String[] args) {
		URL resource = Test.class.getResource("applicationContext.xml");
		ApplicationContext context=new ClassPathXmlApplicationContext(resource.toString());

		StoreService service=(StoreService)context.getBean("storeService") ;
		service.submitOrder();
	}

}
