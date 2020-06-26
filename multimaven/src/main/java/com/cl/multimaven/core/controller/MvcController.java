package com.cl.multimaven.core.controller;


import com.cl.multimaven.core.dao.UserDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * by cl at 2020/5/8 0008
 */
@Controller
@RequestMapping("/mvc")
public class MvcController {
    @Autowired
    private UserDAO userDAO;

    @RequestMapping("/hello")
    @ResponseBody
    public String hello() {
        userDAO.selectByPrimaryKey(2l);
        return "hello";
    }

    @GetMapping("/greeting")
    public String greeting(@RequestParam(name="name", required=false, defaultValue="World") String name, Model model) {
        model.addAttribute("name", name);
        return "greeting";
    }

    @RequestMapping("/index")
    public String index() {
        return "index";
    }

    @RequestMapping("/userlist")
    public String userlist() {
        return "userlist";
    }
}
