package com.cl.controller;

import com.cl.model.UserDO;
import com.cl.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/mvc")
public class UserController {
	@Autowired
	private IUserService userService;

	@RequestMapping("/user/insert")
	public String insertUser() {
		System.out.println("hello 世界");
		UserDO userDO = new UserDO();
		userDO.setName("张三");
		userDO.setAge(11);
		userService.insert(userDO);
		return "hello";
	}


	@RequestMapping(value="/show1")
	public ModelAndView showPerson1(){
		UserDO userDO = new UserDO();
		userDO.setAge(201);
		userDO.setName("jayjay1");
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("p", userDO);
		modelAndView.setViewName("show");
		return modelAndView;
	}

	@RequestMapping(value="/user/{id}",method= RequestMethod.GET)
	public String get(@PathVariable("id") Integer id){
		System.out.println("get"+id);
		return "hello";
	}


	@ResponseBody
	@RequestMapping("/userJson")
	public UserDO userJson(){
		UserDO userDO = new UserDO();
		userDO.setId(11l);
		userDO.setName("jayjay");
		userDO.setAge(11);
		return userDO;
	}
}

