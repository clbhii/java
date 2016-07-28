package com.cl.spring.mvc;

import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.support.RequestContext;

@Controller
@RequestMapping("/mvc")
public class MvcController {

	@RequestMapping("/hello")
	public String hello() {
		return "hello";
	}

	// *********************自动匹配参数*************************

	/**
	 * http://localhost:8090/spring/mvc/person?name=dd&age=1
	 * 
	 * @param name
	 * @param age
	 * @return
	 */
	@RequestMapping("/person")
	public String toPerson(String name, double age) {
		System.out.println(name + " " + age);
		return "hello";
	}

	// *********************自动装箱*************************

	/**
	 * http://localhost:8090/spring/mvc/person1?name=dd&age=1
	 * 
	 * @param name
	 * @param age
	 * @return
	 */
	@RequestMapping("/person1")
	public String toPerson(Person p,User user) {
		System.out.println(p.getName() + " " + p.getAge());
		return "hello";
	}

	// *********************使用InitBinder来处理Date类型的参数*************************

	/**
	 * http://localhost:8090/spring/mvc/date?date=2014-12-22
	 * 
	 * @param date
	 * @return
	 */
	// the parameter was converted in initBinder
	@RequestMapping("/date")
	public String date(Date date) {
		System.out.println(date);
		return "hello";
	}

	// At the time of initialization,convert the type "String" to type "date"
	@InitBinder
	public void initBinder(ServletRequestDataBinder binder) {
		binder.registerCustomEditor(Date.class, new CustomDateEditor(new SimpleDateFormat("yyyy-MM-dd"), true));
	}

	// *********************向前台传递参数**************************
	//Spring MVC 提供Model、ModelMap、Map让我们可以直接添加渲染视图需要的模型数据，
	//在返回时直接指定对应视图名称就可以了。同时Map是继承于ModelMap的，
	//而Model和ModelMap是继承于ExtendedModelMap的。

	@RequestMapping("/show")
	public String showPerson(Map<String, Object> map) {
		Person p = new Person();
		map.put("p", p);
		p.setAge(20);
		p.setName("jayjay");
		return "show";
	}
	
	@RequestMapping(value="/show1")
	public ModelAndView showPerson1(){
		Person p = new Person();
		p.setAge(201);
		p.setName("jayjay1");
	    ModelAndView modelAndView = new ModelAndView();  
	    modelAndView.addObject("p", p);  
	    modelAndView.setViewName("show");  
	    return modelAndView;
	}
	

	// *********************ajax调用*************************

	@RequestMapping("/getPerson")
	public void getPerson(String name, PrintWriter pw) {
		pw.write("hello," + name);
	}
	
	// *********************在Controller中使用redirect方式处理请求*************************
	
	//redirect 
	@RequestMapping("/redirect")
	public String redirect(){
	    return "redirect:hello";
	}
	
	@RequestMapping("/forword")
	public String forword(){
	    return "forward:hello";
	}
	
	// *********************文件上传*************************
	
	@RequestMapping("/form")
	public String form(){
	    return "form";
	}
	@RequestMapping(value="/upload",method=RequestMethod.POST)
	public String upload(HttpServletRequest req) throws Exception{
	    MultipartHttpServletRequest mreq = (MultipartHttpServletRequest)req;
	    MultipartFile file = mreq.getFile("file");
	    String fileName = file.getOriginalFilename();
	    SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");        
	    FileOutputStream fos = new FileOutputStream(req.getSession().getServletContext().getRealPath("/")+
	            "upload/"+sdf.format(new Date())+fileName.substring(fileName.lastIndexOf('.')));
	    fos.write(file.getBytes());
	    fos.flush();
	    fos.close();
	     
	    return "hello";
	}
	
	// *********************使用@RequestParam注解指定参数的name*************************

    @RequestMapping(value="/param")
    public String testRequestParam(@RequestParam(value="id1") Integer id,
            @RequestParam(value="name1")String name){
        System.out.println(id+" "+name);
        return "hello";
    }    
    
    // *********************RESTFul风格的SringMVC*************************
    
    @RequestMapping(value="/user/{id}",method=RequestMethod.GET)
    public String get(@PathVariable("id") Integer id){
        System.out.println("get"+id);
        return "hello";
    }
     
    @RequestMapping(value="/user/{id}",method=RequestMethod.POST)
    public String post(@PathVariable("id") Integer id){
        System.out.println("post"+id);
        return "hello";
    }
     
    @RequestMapping(value="/user/{id}",method=RequestMethod.PUT)
    public String put(@PathVariable("id") Integer id){
        System.out.println("put"+id);
        return "hello";
    }
     
    @RequestMapping(value="/user/{id}",method=RequestMethod.DELETE)
    public String delete(@PathVariable("id") Integer id){
        System.out.println("delete"+id);
        return "hello";
    }
    
    // *********************返回json格式的字符串*************************
	
    @ResponseBody
    @RequestMapping("/personJson")
    public Person get(){
    	Person u = new Person();    
    	u.setName("jayjay");
        return u;
    }
    
    @RequestMapping("/map")
	@ResponseBody
	public String business(HttpServletRequest req, Map<Object, Object> map) {
    	return "success";
    }
    
    // *********************异常处理*************************
    
    //局部异常
    @ExceptionHandler
    public ModelAndView exceptionHandler(Exception ex){
        ModelAndView mv = new ModelAndView("error");
        mv.addObject("exception", ex);
        System.out.println("in testExceptionHandler");       
        return mv;
    }
        
    @RequestMapping("/error")
    public String error(){
        int i = 5/0;
        return "hello";
    }
    
    //全局异常，如果局部异常没捕捉到异常，就交给全局异常
    //全局异常也可以用
    /*
     * <!-- configure SimpleMappingExceptionResolver -->
		<bean class="org.springframework.web.servlet.handler.SimpleMappingExceptionResolver">
		    <property name="exceptionMappings">
		        <props>
		            <prop key="java.lang.ArithmeticException">error</prop>
		        </props>
		    </property>
		</bean>
     */
    @ControllerAdvice
    public static class testControllerAdvice {
    	
        @ExceptionHandler
        public ModelAndView exceptionHandler(Exception ex){
            ModelAndView mv = new ModelAndView("error");
            mv.addObject("exception", ex);
            System.out.println("in testControllerAdvice");
            return mv;
        }
    }
    
    // *********************表单的验证（使用Hibernate-validate） *************************
       
    @RequestMapping(value="/add",method=RequestMethod.POST)    
    public String add(@Valid Person u,BindingResult br){
        if(br.getErrorCount()>0){            
            return "form-validate";
        }
        return "showUser";
    }
     
    @RequestMapping(value="/add",method=RequestMethod.GET)
    public String add(Map<String,Object> map){
        map.put("person",new Person());
        return "form-validate";
    }
    
    
    
    // *********************国际化 *************************
    //优先级
    //Content-Language:en-US 
    //本地（window zh_CN）
    //默认的
    
    @RequestMapping("/locale")
    public String locale(HttpServletRequest request,Model model){
    	 //从后台代码获取国际化信息
    	RequestContext requestContext = new RequestContext(request);
        model.addAttribute("username", requestContext.getMessage("username"));
        return "locale";
    }
    
}

