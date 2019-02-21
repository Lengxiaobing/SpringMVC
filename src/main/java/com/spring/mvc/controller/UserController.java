package com.spring.mvc.controller;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;

/**
 * -@description: 用户处理器-非注解方式
 * <p>
 * -@author: xiang
 * -@create: 2019-02-21 22:10
 */
public class UserController implements Controller {
    @Override
    public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {

        ArrayList<Object> list = new ArrayList<>();
        list.add("name");
        list.add("age");

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("list", list);
        modelAndView.setViewName("WEB-INF/jsp/user.jsp");

        return modelAndView;
    }
}
